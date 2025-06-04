package br.com.quadraja.api.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.quadraja.api.models.Court;
import br.com.quadraja.api.models.User;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {

    @Query("SELECT c FROM Court c WHERE c.active = TRUE AND c.name LIKE :name")
    public List<Court> findAllByName(@Param("name") String name);

}
