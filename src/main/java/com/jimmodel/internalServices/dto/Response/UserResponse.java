package com.jimmodel.internalServices.dto.Response;

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
public class UserResponse {
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Set<RoleResponse> roles;

    public UserResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.emailAddress = user.getEmailAddress();
        this.roles = user.getRoles() != null ? user.getRoles().stream().map(role -> new RoleResponse(role)).collect(Collectors.toSet()) : new HashSet<>();

    }
}
