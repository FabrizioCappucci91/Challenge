package com.challenge.cineMille.security;

import com.challenge.cineMille.model.entity.UserEntity;
import com.challenge.cineMille.model.enums.Ruolo;
import com.challenge.cineMille.model.login.LoginResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final Key secret = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expiration = 1000 * 60 * 60; // 1h
    private final EntityManager em;
    private final BCryptPasswordEncoder encoder;

    public LoginResponse login(String username,String password) {
        try {
            UserEntity user = em.createQuery("from Utenti where username = :username", UserEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();

            if (!encoder.matches(password, user.getPassword())) {
                throw new RuntimeException("Invalid password");
            }

            String token = Jwts.builder()
                    .setSubject(username)
                    .claim("ruoli", user.getRuoli().stream().map(Enum::name).toList())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(secret)
                    .compact();

            return new LoginResponse(token);
        } catch (NoResultException e) {
            throw new RuntimeException("User not found");
        }
    }
    public Set<Ruolo> extractUserRoles(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        List<String> ruoli = (List<String>) claims.get("ruoli");
        return ruoli.stream()
                .map(Ruolo::valueOf)
                .collect(Collectors.toSet());
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
