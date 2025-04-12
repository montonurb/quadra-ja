package br.com.quadraja.api.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.quadraja.api.dtos.SportRequest;
import br.com.quadraja.api.exceptions.SportException;
import br.com.quadraja.api.models.Sport;
import br.com.quadraja.api.repositories.SportRepository;

@Service
public class SportService {
    @Autowired
    private SportRepository sportRepository;

    public List<Sport> findAll() {
        return sportRepository.findAllActive();
    }

    public Sport create(SportRequest sportRequest) {
        Sport sport = new Sport();
        sport.setName(sportRequest.name());
        sport.setRegistrationDate(LocalDateTime.now());
        sport.setActive(true);

        return sportRepository.save(sport);
    }

    public void disable(SportRequest sportRequest) {
        List<Sport> sports = sportRepository.findAllActive();
        if (sports == null || sports.isEmpty() ) {
            throw new SportException("Nenhum sport encontrado com este nome!");
        }
        Sport sport = sports.getFirst();
        sport.setActive(false);
        sportRepository.save(sport);
    }
}
