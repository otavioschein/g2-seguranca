package br.faccat.g2seguranca.controller;

import br.faccat.g2seguranca.entity.User;
import br.faccat.g2seguranca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("")
    public ResponseEntity<List<User>> findUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User newUser) {
        return ResponseEntity.ok(service.updateUser(id, newUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
