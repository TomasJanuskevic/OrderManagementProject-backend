package com.OrderManagement.service;

import com.OrderManagement.exception.UserNotFoundException;
import com.OrderManagement.model.User;
import com.OrderManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id: " + id + " was not found"));
    }

    public void addUser(User user) {
        userRepository.save(user);
        log.info("User was added successfully");
    }

    public void updateUser(User user) throws UserNotFoundException {
        if (userRepository.existsById(user.getUserId())) {
            userRepository.save(user);
            log.info("User was updated successfully");
        } else {
            throw new UserNotFoundException("User by id: " + user.getUserId() + " was not found");
        }
    }

    public void deleteUserById(Long id) throws UserNotFoundException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            log.info("User was deleted successfully");
        } else {
            throw new UserNotFoundException("User by id: " + id + " was not found");
        }

    }
}
