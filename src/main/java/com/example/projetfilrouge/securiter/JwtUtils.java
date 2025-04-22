package com.example.projetfilrouge.securiter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JwtUtils {
    @Value("secret.jwt")
    public String secret;

    public String generateToken(UserDetails userDetails) {

        AppUserDetails appUserDetails = (AppUserDetails) userDetails;

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "azerty")
                .addClaims(Map.of("id", appUserDetails.utilisateur.getIdUser()))
                .addClaims(Map.of("nom", appUserDetails.utilisateur.getNom()))
                .addClaims(Map.of("prenom", appUserDetails.utilisateur.getPrenom()))
                .addClaims(Map.of("motDePasse", appUserDetails.utilisateur.getMotDePasse()))
                .addClaims(Map.of("role", appUserDetails.utilisateur.getRole().getNom()))
                .setSubject(userDetails.getUsername())
                .compact();
    }

    public String getSubjectFromJwt(String jwt) {

        return Jwts.parser()
                .setSigningKey("azerty")
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

}
