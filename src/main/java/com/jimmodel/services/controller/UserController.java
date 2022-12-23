package com.jimmodel.services.controller;

import com.jimmodel.services.dto.Request.UserRequest;
import com.jimmodel.services.dto.Response.UserResponse;
import com.jimmodel.services.dto.Response.UsersResponse;
import com.jimmodel.services.domain.User;
import com.jimmodel.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        User createdUser = this.userService.save(userRequest.toEntity());
        UserResponse responseBody = new UserResponse(createdUser);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(value = "id") UUID id){
        User user = this.userService.findById(id);
        UserResponse responseBody = new UserResponse(user);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable(value = "id") UUID id, @RequestBody UserRequest userRequest){
        User updatedUser = this.userService.saveById(id, userRequest.toEntity());
        UserResponse responseBody= new UserResponse(updatedUser);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ROOT') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<UsersResponse> getUsers(
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "${data.page-size}", name = "pageSize") Integer pageSize,
            @RequestParam(required = false, defaultValue = "${data.user.sort-by}", name = "sortBy") String sortBy,
            @RequestParam(required = false, defaultValue = "${data.sort-dir}", name = "sortDir") String sortDir
    ){
        Page<User> userPage = this.userService.findAll(pageNumber, pageSize, sortBy, sortDir);
        UsersResponse responseBody = new UsersResponse(userPage);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUserById(@PathVariable(value = "id") UUID id){
        this.userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/search/{searchTerm}")
    public ResponseEntity<UsersResponse> searchUsers(
            @PathVariable(name = "searchTerm") String searchTerm,
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "20", name = "pageSize") Integer pageSize,
            @RequestParam(required = false, defaultValue = "firstName", name = "sortBy") String sortBy,
            @RequestParam(required = false, defaultValue = "asc", name = "sortDir") String sortDir
    ){
        Page<User> userPage = this.userService.search(searchTerm, pageNumber, pageSize, sortBy, sortDir);
        UsersResponse responseBody = new UsersResponse(userPage);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
