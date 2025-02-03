package br.com.quadraja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quadraja.api.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
