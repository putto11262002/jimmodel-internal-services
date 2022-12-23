package com.jimmodel.services.domain;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class JwtToken {
    private String accessToken;
    private String refreshToken;
    private Date accessTokenExpiration;
    private Date refreshTokenExpiration;
    private User user;
}
