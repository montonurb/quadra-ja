package br.com.quadraja.api.dtos.request;

import java.util.List;

import br.com.quadraja.api.models.Sport;

public record CourtRequest(String name, List<Sport> sports) {

}
