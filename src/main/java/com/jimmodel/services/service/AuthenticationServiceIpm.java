package com.jimmodel.services.service;

import com.jimmodel.services.domain.JwtToken;
import com.jimmodel.services.domain.User;
import com.jimmodel.services.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service(value = "authenticationService")
public class AuthenticationServiceIpm implements AuthenticationService{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private BlackListUserService blackListUserService;
    @Override
    public JwtToken signIn(String username, String password) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityUtil.Token accessToken = securityUtil.generateAccessToken((UserDetailsImp) authentication.getPrincipal());
        SecurityUtil.Token refreshToken = securityUtil.generateRefreshToken((UserDetailsImp) authentication.getPrincipal());
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        blackListUserService.unblackList(userDetails.getId());
        return JwtToken.builder()
                .accessToken(accessToken.getTokenString())
                .accessTokenExpiration(accessToken.getExpiration())
                .refreshToken(refreshToken.getTokenString())
                .user(User.builder()
                        .username(userDetails.getUsername())
                        .id(userDetails.getId())
                        .lastName(userDetails.getLastName())
                        .firstName(userDetails.getFirstName())
                        .emailAddress(userDetails.getEmailAddress())
                        .build())
                .build();
    }

    @Override
    public JwtToken refresh(String refreshToken) {
        String username = securityUtil.validateRefreshToken(refreshToken);
        UserDetailsImp userDetails = (UserDetailsImp) userService.loadUserByUsername(username);
        SecurityUtil.Token accessToken = securityUtil.generateAccessToken(userDetails);
        return JwtToken.builder()
                .accessToken(accessToken.getTokenString())
                .accessTokenExpiration(accessToken.getExpiration())
                .refreshToken(refreshToken)
                .user(User.builder()
                        .username(userDetails.getUsername())
                        .id(userDetails.getId())
                        .lastName(userDetails.getLastName())
                        .firstName(userDetails.getFirstName())
                        .emailAddress(userDetails.getEmailAddress())
                        .build())
                .build();
    }

    @Override
    public void signOut() {
        if(SecurityContextHolder.getContext().getAuthentication() == null) return;
        UserDetailsImp userDetails = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        blackListUserService.blackList(userDetails.getId(), Duration.ofMillis(securityUtil.REFRESH_TOKEN_EXPIRATION));
    }
}
