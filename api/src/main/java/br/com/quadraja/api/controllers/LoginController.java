package br.com.quadraja.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadraja.api.dtos.UserRequest;
import br.com.quadraja.api.models.User;
import br.com.quadraja.api.services.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> login(@RequestBody UserRequest userRequest) {
        User user = userService.find(userRequest);
        
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }
}
