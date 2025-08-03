package br.com.quadraja.api.models;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class EstablishmentConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean active;
    private LocalTime start;
    private LocalTime finish;
    private Set<DayOfWeek> days;
    private boolean available;
}
