package com.jimmodel.internalServices.config;

import com.jimmodel.internalServices.model.ERole;
import com.jimmodel.internalServices.model.Role;
import com.jimmodel.internalServices.model.User;
import com.jimmodel.internalServices.service.RoleService;
import com.jimmodel.internalServices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Value("${root-user.password}")
    String rootUserPassword;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        // save roles
       for (ERole role : ERole.values()){
           roleService.save(new Role(role));
       }
        // save root user
        userService.save(User.builder().firstName("root").lastName("root").username("root").password(rootUserPassword).roles(Set.of(new Role(ERole.ROLE_ROOT))).build());
    }
}
