package com.jimmodel.internalServices.util;

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
    public long REFRESH_TOKEN_EXPIRATION;

    @Value("${jwt.access-token-secret}")
    public String ACCESS_TOKEN_SECRET;

    @Value("${jwt.authorities-key}")
    public String AUTHORITIES_KEY;

    public String getUsernameFromAccessToken(String authToken){
        return Jwts.parser().setSigningKey(ACCESS_TOKEN_SECRET).parseClaimsJws(authToken).getBody().getSubject();
    }

    public Date getExpirationFromAccessToken(String authToken){
        return Jwts.parser().setSigningKey(ACCESS_TOKEN_SECRET).parseClaimsJws(authToken).getBody().getExpiration();
    }


    public Boolean validateAccessToken(String token, UserDetails userDetails){
        String username = getUsernameFromAccessToken(token);
        return username.equals(userDetails.getUsername()) && !getExpirationFromAccessToken(token).before(new Date(System.currentTimeMillis()));
    }

    public Token generateAccessToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        Date expiration = new Date(Instant.now().toEpochMilli() + REFRESH_TOKEN_EXPIRATION);
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET).compact();
        return new Token((UserDetails) authentication.getPrincipal(), token, expiration);

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
        private String accessToken;
        private Date expiration;
    }


}
