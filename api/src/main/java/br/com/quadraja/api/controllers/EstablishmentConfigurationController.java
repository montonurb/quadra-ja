package br.com.quadraja.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadraja.api.dtos.request.EstablishmentConfigurationRequest;
import br.com.quadraja.api.exceptions.GenericException;
import br.com.quadraja.api.models.EstablishmentConfiguration;
import br.com.quadraja.api.services.EstablishmentConfigurationService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/establishmentConfiguration")
public class EstablishmentConfigurationController {
    @Autowired
    private EstablishmentConfigurationService establishmentConfigurationService;

    @Transactional
    @PostMapping
    public ResponseEntity<EstablishmentConfiguration> createOrSave(@RequestBody EstablishmentConfigurationRequest ecRequest) {
        return ResponseEntity.ok().body(establishmentConfigurationService.createOrSave(ecRequest));
    }

    @GetMapping
    public ResponseEntity<EstablishmentConfiguration> find() {
        System.out.println("Buscando configuracao do estabelecimento...");
        try {
            return ResponseEntity.ok().body(establishmentConfigurationService.find());
        } catch (Exception exception) {
            throw new GenericException("Erro ao obter Configuração do Estabelecimento! " + exception.getMessage());
        }
    }
}
