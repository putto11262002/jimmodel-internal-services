package com.jimmodel.internalServices.dto.Request;

import com.jimmodel.internalServices.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRequest {
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Set<RoleRequest> roles;

    public User toEntity(){
        return User.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .emailAddress(this.emailAddress)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .roles(this.roles != null ? this.roles.stream().map(roleRequest -> roleRequest.toEntity()).collect(Collectors.toSet()) : new HashSet<>())
                .build();
    }
}
