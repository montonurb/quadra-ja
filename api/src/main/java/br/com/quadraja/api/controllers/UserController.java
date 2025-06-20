package br.com.quadraja.api.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadraja.api.dtos.request.UserRequest;
import br.com.quadraja.api.dtos.response.UserResponse;
import br.com.quadraja.api.models.User;
import br.com.quadraja.api.services.UserService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/users")
//@SecurityRequirement(name = "bearer-key")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(new UserResponse(userService.create(userRequest)));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findAll());
    }

    @PatchMapping
    @Transactional
    public ResponseEntity<String> disableUser(@RequestBody UserRequest userRequest) {
        userService.disable(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário desabilitado com sucesso!");
    }
}
