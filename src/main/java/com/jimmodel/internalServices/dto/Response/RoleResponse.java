package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleResponse {

    private String name;
    private String description;

    public RoleResponse(Role role){
        this.name = role.getName();
        this.description = role.getDescription();
    }
}
