package br.com.quadraja.api.repositories;

import br.com.quadraja.api.models.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.quadraja.api.models.Booking;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.court.id = :idCourt AND b.active = TRUE AND b.startTime < b.endTime AND b.endTime > b.startTime")
    List<Booking> isThereTimeAlreadyReserved(LocalDateTime startTime, LocalDateTime endTime, Long idCourt);
}
