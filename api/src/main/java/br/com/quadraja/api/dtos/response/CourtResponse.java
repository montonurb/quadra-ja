package br.com.quadraja.api.dtos.response;

import br.com.quadraja.api.dtos.request.CourtRequest;
import br.com.quadraja.api.models.Court;
import br.com.quadraja.api.models.Sport;

import java.util.List;

public record CourtResponse(Long id, String name, List<Sport> sports) {
    public CourtResponse(Court court) {
        this(court.getId(), court.getName(), court.getSports());
    }
}
