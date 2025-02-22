package br.com.quadraja.api.dtos;

import br.com.quadraja.api.enums.Rule;

public record UserDTO(String name, String email, String password, Rule rule) {

}
