package com.jimmodel.services.util;

import com.jimmodel.services.service.UserDetailsImp;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class SecurityUtil {

    @Value("${jwt.access-token-expiration-ms}")
    public long ACCESS_TOKEN_EXPIRATION;

    @Value("${jwt.access-token-secret}")
    public String ACCESS_TOKEN_SECRET;

    @Value("${jwt.authorities-key}")
    public String AUTHORITIES_KEY;

    @Value("${jwt.refresh-token-expiration-ms}")
    public long REFRESH_TOKEN_EXPIRATION;
    @Value("${jwt.refresh-token-secret}")
    public String REFRESH_TOKEN_SECRET;

    private String getUsernameFromToken(String token, String secret){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    private Date getExpirationFromToken(String token, String secret){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
    }


    public String validateAccessToken(String token){
        return getUsernameFromToken(token, ACCESS_TOKEN_SECRET);
    }

    public Token generateAccessToken(UserDetailsImp userDetails){
        String authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        Date expiration = new Date(Instant.now().toEpochMilli() + ACCESS_TOKEN_EXPIRATION);
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET).compact();
        return new Token(userDetails, token, expiration);

    }

    public Token generateRefreshToken(UserDetailsImp userDetails){
        String authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        Date expiration = new Date(Instant.now().toEpochMilli() + REFRESH_TOKEN_EXPIRATION);
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, REFRESH_TOKEN_SECRET).compact();
        return new Token(userDetails, token, expiration);

    }

//    public Token generateRefreshToken(Authentication authentication){
//        Date expiration = new Date(Instant.now().toEpochMilli() + REFRESH_TOKEN_EXPIRATION);
//        String token = Jwts.builder()
//                .setSubject(authentication.getName())
//                .setIssuedAt(new Date())
//                .setExpiration(expiration)
//                .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET).compact();
//        return new Token((UserDetails) authentication.getPrincipal(), token, expiration);
//
//    }

    public String validateRefreshToken(String token){
        return getUsernameFromToken(token, REFRESH_TOKEN_SECRET);
    }

    public UsernamePasswordAuthenticationToken getAuthenticationToken(String token, Authentication authentication, UserDetails userDetails){
        JwtParser jwtParser = Jwts.parser().setSigningKey(ACCESS_TOKEN_SECRET);
        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Collection<? extends  GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public class Token {
        private UserDetails username;
        private String tokenString;
        private Date expiration;
    }


}
