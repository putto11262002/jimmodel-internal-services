package com.jimmodel.internalServices.dto.Request;

import com.jimmodel.internalServices.model.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoleRequest {

    private String name;
    private String description;

    public Role toEntity(){
        return Role.builder()
                .name(this.name)
                .description(this.description)
                .build();
    }
}
