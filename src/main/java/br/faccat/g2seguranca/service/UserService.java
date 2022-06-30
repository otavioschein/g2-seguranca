package br.faccat.g2seguranca.service;

import br.faccat.g2seguranca.entity.User;
import br.faccat.g2seguranca.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public User createUser(User user) {
        if (validateUser(user))
            return repository.save(user);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User must not be null.");
    }

    public User updateUser(Integer id, User newUser) {
         Optional<User> actualUser = repository.findById(id);
         if(validateUser(newUser)) {
             if (actualUser.isPresent()) {
                 BeanUtils.copyProperties(newUser, actualUser.get(), "code");
                 return repository.save(actualUser.get());
             }
             throw new ResponseStatusException(HttpStatus.NOT_FOUND);
         }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User must not be null.");
    }

    public void deleteUser(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private boolean validateUser(User user) {
        if(Objects.isNull(user.getName())
                || Objects.isNull(user.getEmail())
                || Objects.isNull(user.getProfessionalExperience())) {
            return false;
        }
        return true;
    }

}
