package com.OrderManagement.controller;

import com.OrderManagement.exception.UserNotFoundException;
import com.OrderManagement.model.AuthRequest;
import com.OrderManagement.model.User;
import com.OrderManagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<User>> findUsers() {
        return new ResponseEntity<>(userService.findUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/user")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/user")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> updateUser(@RequestBody User user) throws UserNotFoundException {
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
