package br.com.quadraja.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadraja.api.dtos.SportDTO;
import br.com.quadraja.api.exceptions.SportException;
import br.com.quadraja.api.models.Sport;
import br.com.quadraja.api.services.SportService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/sports")
public class SportController {
    @Autowired
    private SportService sportService;

    @PostMapping
    @Transactional
    public ResponseEntity<Sport> create(@RequestBody SportDTO sportDTO) {
        return ResponseEntity.ok().body(sportService.create(sportDTO));
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
    public ResponseEntity disable(@RequestBody SportDTO sportDTO) {
        sportService.disable(sportDTO);

        return ResponseEntity.ok().body("Sport desabilitado com sucesso!");
    }

}
