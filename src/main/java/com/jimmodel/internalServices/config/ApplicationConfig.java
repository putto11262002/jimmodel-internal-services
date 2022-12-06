package com.jimmodel.internalServices.config;

import com.jimmodel.internalServices.util.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Getter
@Configuration
public class ApplicationConfig {

    private static final String STORAGE_ROOT = "upload";

    private static final String VERSION = "v1";


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public  JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }

}
