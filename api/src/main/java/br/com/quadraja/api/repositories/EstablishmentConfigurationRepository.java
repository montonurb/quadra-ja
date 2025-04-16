package br.com.quadraja.api.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.quadraja.api.models.EstablishmentConfiguration;

@Repository
public interface EstablishmentConfigurationRepository extends JpaRepository<EstablishmentConfiguration, Long> {

    @Query("SELECT ec FROM EstablishmentConfiguration ec WHERE ec.active = TRUE")
    Optional<EstablishmentConfiguration> find();

}
