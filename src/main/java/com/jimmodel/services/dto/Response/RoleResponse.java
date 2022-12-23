package com.jimmodel.services.dto.Response;

import com.jimmodel.services.domain.Role;
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

    public RoleResponse(Role role){
        this.name = role.getName().name();
    }
}
