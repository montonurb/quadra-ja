package br.com.quadraja.api.services;

import java.time.LocalDateTime;
import java.util.List;

import br.com.quadraja.api.exceptions.CourtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.quadraja.api.dtos.request.CourtRequest;
import br.com.quadraja.api.models.Court;
import br.com.quadraja.api.repositories.CourtRepository;

@Service
public class CourtService {
    @Autowired
    CourtRepository courtRepository;

    public Court create(CourtRequest courtRequest) {
        Court court = new Court();
        court.setActive(true);
        court.setName(courtRequest.name());
        court.setSports(courtRequest.sports());

        List<Court> courts = courtRepository.findAllByName(courtRequest.name());

        if (courts != null && !courts.isEmpty()) {
            throw new CourtException("Já existe uma quadra cadastrada com esse nome!");
        } else {
            courtRepository.save(court);
        }

        return court;
    }

    public Court getById(Long id) {
        return courtRepository.getReferenceById(id);
    }
}
