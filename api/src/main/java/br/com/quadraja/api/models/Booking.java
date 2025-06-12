package br.com.quadraja.api.models;

import java.time.LocalDateTime;
import br.com.quadraja.api.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean active;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private LocalDateTime registrationDate;
    @ManyToOne
    private User userReservation;
    @ManyToOne
    private User userRegister;
    @ManyToOne
    private Court court;
}

