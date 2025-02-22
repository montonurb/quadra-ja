package br.com.quadraja.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadraja.api.dtos.UserDTO;
import br.com.quadraja.api.services.UserService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDTO));
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findAll());
    }

    @PatchMapping
    @Transactional
    public ResponseEntity disableUser(@RequestBody UserDTO userDTO) {
        userService.disable(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário desabilitado com sucesso!");
    }
}
