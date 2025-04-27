package br.com.quadraja.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.quadraja.api.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.active = TRUE AND u.email LIKE :email AND u.password LIKE :password")
    List<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT u FROM User u WHERE u.active = TRUE AND u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.active = TRUE AND u.email LIKE :email")
    List<User> findUsersByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.active = TRUE")
    List<User> findAllActive();
}
