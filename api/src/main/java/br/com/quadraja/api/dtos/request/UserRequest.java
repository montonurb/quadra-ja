package br.com.quadraja.api.dtos.request;

import br.com.quadraja.api.enums.Rule;

public record UserRequest(String name, String email, String password, Rule rule) {

}
