package com.rest_api.fs14backend.utils;

import com.rest_api.fs14backend.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtils {
  @Autowired
  private Environment environment;

  public String generateToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("user_id", user.getId());
    claims.put("email", user.getEmail());
    claims.put("role", user.getRoles());

    return createToken(claims, user.getEmail());
  }

  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(SignatureAlgorithm.HS256, environment.getProperty("JWT_SECRET"))
            .compact();

  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
            .setSigningKey(environment.getProperty("JWT_SECRET"))
            .parseClaimsJws(token)
            .getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String email = extractUsername(token);
    return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}