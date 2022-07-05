package br.faccat.g2seguranca.service;

import br.faccat.g2seguranca.entity.User;
import br.faccat.g2seguranca.entity.UserDto;
import br.faccat.g2seguranca.mapper.Mapper;
import br.faccat.g2seguranca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private Mapper mapper;

    public List<UserDto> getUsers() {
        List<User> users = repository.findAll();
        return mapper.toUserDto(users);
    }

    public User getUserByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public User createUser(User user) {
        if (validateUser(user)) {
            System.out.println(user.toString());
            return repository.save(user);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User must not be null.");
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
