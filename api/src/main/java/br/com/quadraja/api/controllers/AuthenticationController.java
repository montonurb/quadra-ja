package br.com.quadraja.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadraja.api.dtos.request.AuthenticationRequest;
import br.com.quadraja.api.infra.security.TokenJWT;
import br.com.quadraja.api.models.User;
import br.com.quadraja.api.services.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJWT> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.email(), authenticationRequest.password());
        var authentication = manager.authenticate(authenticationToken);

        final String token = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWT(token));

    }
}
