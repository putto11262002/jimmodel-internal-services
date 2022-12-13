package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.exception.ConstraintViolationException;
import com.jimmodel.internalServices.model.*;
import com.jimmodel.internalServices.exception.ResourceNotFoundException;
import com.jimmodel.internalServices.exception.ValidationException;
import com.jimmodel.internalServices.repository.RoleRepository;
import com.jimmodel.internalServices.repository.UserRepository;
import com.jimmodel.internalServices.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service(value = "userService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Validator validator;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    SecurityUtil securityUtil;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException(String.format("User with username %s does not exist", username)));
        return UserDetailsImp.build(user);
    }

    @Override
    public User save(User user) {
       Set<ConstraintViolation<BaseEntity>> violations = validator.validate(user, User.NewUserInfo.class);
       if(!violations.isEmpty()){
           throw new ValidationException("User validation failed", violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList()));
       }

       if(userRepository.existsByUsername(user.getUsername())){
           throw new ConstraintViolationException(String.format("User with username %s already exist.", user.getUsername()));
       }
       // get roles
       // check if user have the privilege to insert user with the given role
       if(user.getRoles().contains(new Role(ERole.ROLE_ROOT))) {
           if(userRepository.existsByRoles(new Role(ERole.ROLE_ROOT))){
               throw new ConstraintViolationException("Root user already exist.");
           }
       }

//       if(user.getRoles().contains(RoleServiceImp.ADMIN) & SecurityContextHolder.getContext().getAuthentication() != null ){
//           if (!SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_root"))) throw new AccessDeniedException("Unauthorised operation.");
//       }


       user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

    @Override
    public User saveById(UUID id, User updatedUpdate) {
        UserDetailsImp userDetails = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getId() != id || !SecurityContextHolder.getContext().getAuthentication().getAuthorities().containsAll(List.of( new SimpleGrantedAuthority("ROLE_root")))){
            throw new AccessDeniedException("Forbidden operation.");
        }


        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %s does not exist", id)));
        Set<ConstraintViolation<BaseEntity>> violations = validator.validate(updatedUpdate, User.ExistingUserInfo.class);
        if(!violations.isEmpty()){
            throw new ValidationException("User validation failed", violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList()));
        }
        user.setLastName(updatedUpdate.getLastName());
        user.setEmailAddress(updatedUpdate.getEmailAddress());
        user.setFirstName(updatedUpdate.getFirstName());
        return userRepository.save(user);
    }

    @Override
    public void changeUsername(UUID id, String newUsername) {
        UserDetailsImp userDetails = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getId() != id || !SecurityContextHolder.getContext().getAuthentication().getAuthorities().containsAll(List.of(new SimpleGrantedAuthority(ERole.ROLE_ROOT.name())))){
            throw new AccessDeniedException("Forbidden operation.");
        }
        if(userRepository.existsByUsername(newUsername)){
            throw new ConstraintViolationException(String.format("User with username %s already exist.",newUsername));
        }
        User user = userRepository.findById(id).orElseThrow(() -> new ConstraintViolationException(String.format("User with username %s already exist.", id)));
        user.setUsername(newUsername);
        userRepository.save(user);
    }

    @Override
    public void changePassword(UUID id, String newPassword) {
        UserDetailsImp userDetails = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getId() != id || !SecurityContextHolder.getContext().getAuthentication().getAuthorities().containsAll(List.of(new SimpleGrantedAuthority(ERole.ROLE_ROOT.name())))){
            throw new AccessDeniedException("Forbidden operation.");
        }
        User user = userRepository.findById(id).orElseThrow(() -> new ConstraintViolationException(String.format("User with username %s already exist.", id)));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void changeUserRole(UUID id, Set<Role> newRoles) {
        User user = userRepository.findById(id).orElseThrow(() -> new ConstraintViolationException(String.format("User with username %s already exist.", id)));
        newRoles.remove(new Role(ERole.ROLE_ROOT));
        user.setRoles(newRoles);
        userRepository.save(user);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %s does not exist", id)));
    }

    @Override
    public Page<User> findAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return userRepository.findAll(pageable);
    }

    @Override
    public void deleteById(UUID id) {
        if(userRepository.existsById(id)){
            new ResourceNotFoundException(String.format("User with id %s does not exist", id));
        }
        userRepository.deleteById(id);
    }

    @Override
    public JwtToken signIn(String username, String password) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityUtil.Token token = securityUtil.generateAccessToken(authentication);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        return JwtToken.builder()
                .accessToken(token.getAccessToken())
                .accessTokenExpiration(token.getExpiration())
                .userId(userDetails.getId())
                .build();
    }

    @Override
    public Page<User> search(String searchTerm, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return userRepository.search(searchTerm, pageable);
    }
}
