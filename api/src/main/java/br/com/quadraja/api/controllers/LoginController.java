package br.com.quadraja.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadraja.api.dtos.UserDTO;
import br.com.quadraja.api.models.User;
import br.com.quadraja.api.services.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity login(@RequestBody UserDTO userDTO) {
        User user = userService.find(userDTO);
        if (user != null && user.getId() != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado !");
        }
    }
}
