package br.com.quadraja.api.controllers;

import br.com.quadraja.api.dtos.request.BookingRequest;
import br.com.quadraja.api.dtos.response.BookingResponse;
import br.com.quadraja.api.services.BookingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/reservation")
@SecurityRequirement(name = "bearer-key")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    @Transactional
    public ResponseEntity<BookingResponse> create(@RequestBody BookingRequest bookingRequest, @RequestHeader("Authorization") String authorization) {

        return ResponseEntity.status(HttpStatus.OK).body(bookingService.create(bookingRequest, authorization.replaceAll("Bearer ", "")));
    }

    public ResponseEntity<BookingResponse> findFreeTime(LocalDate dayOfTheWeek) {
        bookingService.getFreeTime(dayOfTheWeek);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
