package com.jimmodel.internalServices.controller;

import com.jimmodel.internalServices.dto.Request.SignInRequest;
import com.jimmodel.internalServices.dto.Request.UserRequest;
import com.jimmodel.internalServices.dto.Response.JwtTokenResponse;
import com.jimmodel.internalServices.dto.Response.UserResponse;
import com.jimmodel.internalServices.model.JwtToken;
import com.jimmodel.internalServices.model.User;
import com.jimmodel.internalServices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        User createdUser = userService.save(userRequest.toEntity());
        UserResponse responseBody = new UserResponse(createdUser);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtTokenResponse> signIn(@RequestBody SignInRequest signInRequest){
        JwtToken jwtToken = userService.signIn(signInRequest.getUsername(), signInRequest.getPassword());
        JwtTokenResponse responseBody = new JwtTokenResponse(jwtToken);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
