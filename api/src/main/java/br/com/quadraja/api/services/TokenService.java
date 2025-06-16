package br.com.quadraja.api.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.quadraja.api.models.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("API QuadraJa")
                .withSubject(user.getEmail())
                .withExpiresAt(dateExpiration())
                .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Ocorreu um erro ao gerar o Token! Erro: ", e);
        }
    }

    public String getSubject(String tokenJWT) {
        System.out.println("Token:" + tokenJWT);
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("API QuadraJa")
                .build()
                .verify(tokenJWT)
                .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido ou expirado!");
        }
    }

    private Instant dateExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
