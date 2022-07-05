package br.faccat.g2seguranca.repository;

import br.faccat.g2seguranca.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from user where name = ?1", nativeQuery = true)
    Optional<User> findByName(String name);

}