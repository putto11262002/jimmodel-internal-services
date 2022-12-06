package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.model.BaseEntity;
import com.jimmodel.internalServices.model.JwtToken;
import com.jimmodel.internalServices.model.Role;
import com.jimmodel.internalServices.model.User;
import com.jimmodel.internalServices.exception.ResourceNotFoundException;
import com.jimmodel.internalServices.exception.ValidationException;
import com.jimmodel.internalServices.repository.UserRepository;
import com.jimmodel.internalServices.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Validator validator;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException(String.format("User with username %s does not exist", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toSet())
                );
    }

    @Override
    public User save(User user) {
       Set<ConstraintViolation<BaseEntity>> violations = validator.validate(user);
       if(!violations.isEmpty()){
           throw new ValidationException("User validation failed", violations);
       }
       Role role = roleService.findByName("Booker");
       user.addRole(role);
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

    @Override
    public User saveById(UUID id, User updatedUpdate) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %s does not exist", id)));
        Set<ConstraintViolation<BaseEntity>> violations = validator.validate(updatedUpdate);
        if(!violations.isEmpty()){
            throw new ValidationException("User validation failed", violations);
        }
        user.setLastName(updatedUpdate.getLastName());
        user.setEmailAddress(updatedUpdate.getEmailAddress());
        user.setFirstName(updatedUpdate.getFirstName());
        return userRepository.save(user);

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
        System.out.println(authentication.getName());
        JwtToken jwtToken = jwtUtil.generateToken(authentication);
        return jwtToken;
    }

    @Override
    public Page<User> search(String searchTerm, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return userRepository.search(searchTerm, pageable);
    }
}
