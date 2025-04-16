package br.com.quadraja.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quadraja.api.dtos.EstablishmentConfigurationRequest;
import br.com.quadraja.api.exceptions.GenericException;
import br.com.quadraja.api.models.EstablishmentConfiguration;
import br.com.quadraja.api.services.EstablishmentConfigurationService;

@RestController
@RequestMapping("/establishmentConfiguration")
public class EstablishmentConfigurationController {
    @Autowired
    private EstablishmentConfigurationService establishmentConfigurationService;

    @PostMapping
    public ResponseEntity<EstablishmentConfiguration> createOrSave(EstablishmentConfigurationRequest ecRequest) {
        return ResponseEntity.ok().body(establishmentConfigurationService.createOrSave(ecRequest));
    }

    @GetMapping
    public ResponseEntity<EstablishmentConfiguration> find() {
        try {
            return ResponseEntity.ok().body(establishmentConfigurationService.find());
        } catch (Exception exception) {
            throw new GenericException("Erro ao obter Configuração do Estabelecimento! " + exception.getMessage());
        }
    }
}
