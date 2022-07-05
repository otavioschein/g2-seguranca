package br.faccat.g2seguranca.controller;

import br.faccat.g2seguranca.entity.User;
import br.faccat.g2seguranca.entity.UserDto;
import br.faccat.g2seguranca.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> findUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping("/")
    public ResponseEntity<User> findUserByName(@PathParam("name") String name) {
        log.info("[CONTROLLER] - Name: {}", name);
        return ResponseEntity.ok(service.getUserByName(name));
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("[CONTROLLER] - Payload: {}", user);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
    }

}
