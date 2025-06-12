package br.com.quadraja.api.controllers;

import br.com.quadraja.api.dtos.request.BookingRequest;
import br.com.quadraja.api.dtos.response.BookingResponse;
import br.com.quadraja.api.services.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    @Transactional
    public ResponseEntity<BookingResponse> create(@RequestBody BookingRequest bookingRequest, @RequestHeader("Authorization") String authorization) {

        return ResponseEntity.status(HttpStatus.OK).body(bookingService.create(bookingRequest, authorization.replaceAll("Bearer ", "")));
    }

}
