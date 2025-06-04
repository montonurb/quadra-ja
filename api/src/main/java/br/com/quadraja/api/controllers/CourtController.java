package br.com.quadraja.api.controllers;

import br.com.quadraja.api.dtos.request.CourtRequest;
import br.com.quadraja.api.dtos.response.CourtResponse;
import br.com.quadraja.api.services.CourtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/court")
public class CourtController {
    @Autowired
    CourtService courtService;

    @PostMapping
    @Transactional
    public ResponseEntity<CourtResponse> create(@RequestBody CourtRequest request) {
        return ResponseEntity.ok(new CourtResponse(courtService.create(request)));
    }
}
