package br.com.quadraja.api.dtos.request;

import br.com.quadraja.api.enums.Status;
import java.time.LocalDateTime;

public record BookingRequest(LocalDateTime startTime, LocalDateTime endTime, Status status, Long idUserReservation, Long idCourt) {
}
