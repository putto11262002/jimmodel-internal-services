package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.model.JwtToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JwtTokenResponse {
    private String username;
    private String accessToken;
    private Date expiration;

    public JwtTokenResponse(JwtToken jwtToken){
        this.username = jwtToken.getUsername();
        this.accessToken = jwtToken.getAccessToken();
        this.expiration = jwtToken.getExpiration();
    }
}
