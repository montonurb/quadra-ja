package br.com.quadraja.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadraja.api.dtos.AuthenticationRequest;
import br.com.quadraja.api.models.User;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;


    @PostMapping
    public ResponseEntity<User> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        var token = new UsernamePasswordAuthenticationToken(authenticationRequest.email(), authenticationRequest.password());
        var authentication = manager.authenticate(token);
        
        return ResponseEntity.ok().build();
    }
}
