package br.com.quadraja.api.dtos.response;

import br.com.quadraja.api.models.Booking;

import java.time.LocalDateTime;

public record BookingResponse(Long id, String courtName, String userName, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime registrationDate, String status) {
    public BookingResponse(Booking booking) {
        this(booking.getId(), booking.getCourt().getName(), booking.getUserReservation().getName(), booking.getStartTime(), booking.getEndTime(), booking.getRegistrationDate(), booking.getStatus().name());
    }
}
