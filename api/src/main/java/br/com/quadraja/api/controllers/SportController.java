package br.com.quadraja.api.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadraja.api.dtos.request.SportRequest;
import br.com.quadraja.api.exceptions.SportException;
import br.com.quadraja.api.models.Sport;
import br.com.quadraja.api.services.SportService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/sports")
@SecurityRequirement(name = "bearer-key")
public class SportController {
    @Autowired
    private SportService sportService;

    @PostMapping
    @Transactional
    public ResponseEntity<Sport> create(@RequestBody SportRequest sportRequest) {
        return ResponseEntity.ok().body(sportService.create(sportRequest));
    }

    @GetMapping
    public ResponseEntity<List<Sport>> findAll() {
        try {
            return ResponseEntity.ok().body(sportService.findAll());
        } catch (Exception exception) {
            throw new SportException("Erro! " + exception.getMessage());
        }
    }

    @PatchMapping
    @Transactional
    public ResponseEntity<String> disable(@RequestBody SportRequest sportRequest) {
        sportService.disable(sportRequest);

        return ResponseEntity.ok().body("Sport desabilitado com sucesso!");
    }

}
