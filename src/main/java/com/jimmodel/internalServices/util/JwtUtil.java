package com.jimmodel.internalServices.util;

import com.jimmodel.internalServices.model.JwtToken;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtUtil {

    @Value("${jwt.token-validity}")
    public long TOKEN_VALIDITY;

    @Value("${jwt.signing-key}")
    public String SIGNING_KEY;

    @Value("${jwt.authorities-key}")
    public String AUTHORITIES_KEY;

    public String getUsernameFromToken(String authToken){
        return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(authToken).getBody().getSubject();
    }

    public Date getExpiration(String authToken){
        return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(authToken).getBody().getExpiration();
    }


    public Boolean validate(String token, UserDetails userDetails){
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !getExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    public JwtToken generateToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        Long now = System.currentTimeMillis();
        Long expiration = now + TOKEN_VALIDITY * 1000;
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(expiration))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY).compact();
        return new JwtToken( authentication.getName(), token, new Date(expiration));

    }

    public UsernamePasswordAuthenticationToken getAuthenticationToken(String token, Authentication authentication, UserDetails userDetails){
        JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);
        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Collection<? extends  GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }


}
