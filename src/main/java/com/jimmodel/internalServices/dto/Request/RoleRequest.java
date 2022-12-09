package com.jimmodel.internalServices.dto.Request;

import com.jimmodel.internalServices.model.ERole;
import com.jimmodel.internalServices.model.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoleRequest {

    private String name;

    public Role toEntity(){
        ERole eRole;
        switch (this.name.toLowerCase()){
            case "admin":
                eRole = ERole.ROLE_ADMIN;
                break;
            case "root":
                eRole = ERole.ROLE_ROOT;
                break;
            case "user":
                eRole = ERole.ROLE_USER;
                break;
            default:
                eRole = null;
        }
        return Role.builder()
                .name(eRole)
                .build();
    }
}
