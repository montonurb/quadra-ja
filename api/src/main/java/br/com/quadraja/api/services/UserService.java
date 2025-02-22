package br.com.quadraja.api.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.quadraja.api.dtos.UserDTO;
import br.com.quadraja.api.exceptions.UserException;
import br.com.quadraja.api.models.User;
import br.com.quadraja.api.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User create(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setRule(userDTO.rule());
        user.setRegistrationDate(LocalDateTime.now());
        user.setActive(true);
        // user.setPassword(encryptPassword(userDTO.password()));

        List<User> users = userRepository.findByEmail(user.getEmail());
            
        if (users != null && !users.isEmpty()) {
            throw new UserException("Já existe um usuário cadastrado com esse e-mail!");
        } else {
            userRepository.save(user);
        }

        return user;
    }

    public User find(UserDTO userDTO) {
        List<User> users = userRepository.findByEmailAndPassword(userDTO.email(), userDTO.password());

        return users.getFirst();
    }

    public List<User> findAll() {
        return userRepository.findAllActive();
    }

    public void disable(UserDTO userDTO) {
        List<User> users = userRepository.findByEmail(userDTO.email());
        if (users == null || users.isEmpty()) {
            throw new UserException("Nenhum usuário encontrado com esse e-mail!");
        }
        User user = users.getFirst();
        user.setActive(false);
        userRepository.save(user);
    }

    // private String encryptPassword(String password) {
    //     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    //     return encoder.encode(password);
    // }
}
