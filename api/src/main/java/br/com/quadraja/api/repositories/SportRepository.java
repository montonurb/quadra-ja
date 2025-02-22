package br.com.quadraja.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.quadraja.api.models.Sport;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {

    @Query("SELECT s FROM Sport s WHERE s.active = TRUE")
    List<Sport> findAllActive();

}
