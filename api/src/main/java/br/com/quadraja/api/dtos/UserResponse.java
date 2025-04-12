package br.com.quadraja.api.dtos;

import br.com.quadraja.api.models.User;

public record UserResponse(String name, String email, String password) {
    public UserResponse(User user) {
        this(user.getName(), user.getEmail(), user.getPassword());
    }
}
