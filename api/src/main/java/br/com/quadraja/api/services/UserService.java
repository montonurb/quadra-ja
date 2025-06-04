package br.com.quadraja.api.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.quadraja.api.dtos.request.UserRequest;
import br.com.quadraja.api.exceptions.UserException;
import br.com.quadraja.api.models.User;
import br.com.quadraja.api.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User create(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setRule(userRequest.rule());
        user.setRegistrationDate(LocalDateTime.now());
        user.setActive(true);
        user.setPassword(encryptPassword(userRequest.password()));

        List<User> users = userRepository.findUsersByEmail(user.getEmail());
            
        if (users != null && !users.isEmpty()) {
            throw new UserException("Já existe um usuário cadastrado com esse e-mail!");
        } else {
            userRepository.save(user);
        }

        return user;
    }

    public User find(UserRequest userRequest) {
        List<User> users = userRepository.findByEmailAndPassword(userRequest.email(), encryptPassword(userRequest.password()));

        return users.getFirst();
    }

    public User findByEmail(String email) {
        System.out.println("buscando user...");
        return userRepository.findByEmail(email).orElseThrow();
    }

    public List<User> findAll() {
        return userRepository.findAllActive();
    }

    public void disable(UserRequest userRequest) {
        List<User> users = userRepository.findUsersByEmail(userRequest.email());
        if (users == null || users.isEmpty()) {
            throw new UserException("Nenhum usuário encontrado com esse e-mail!");
        }
        User user = users.getFirst();
        user.setActive(false);
        userRepository.save(user);
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(password);
    }
}
