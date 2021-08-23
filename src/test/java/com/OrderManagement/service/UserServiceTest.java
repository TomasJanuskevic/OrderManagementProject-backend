package com.OrderManagement.service;

import com.OrderManagement.exception.UserNotFoundException;
import com.OrderManagement.model.User;
import com.OrderManagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userTestRepository;
    private UserService userTestService;

    @BeforeEach
    void setUp() {
        userTestService = new UserService(userTestRepository);
    }

    @Test
    void findUsers() {
        userTestService.findUsers();
        verify(userTestRepository).findAll();
    }

    @Test
    void getUserById() throws UserNotFoundException {
        Long userId = 1L;
        User user = new User("Bob", "password");
        when(userTestRepository.findById(userId)).thenReturn(Optional.of(user));
        assertThat(userTestService.getUserById(userId)).isEqualTo(user);
    }

    @Test
    void addUser() {
        User user = new User("Bob", "password");
        userTestService.addUser(user);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userTestRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    @Disabled
    void updateUser() {
    }

    @Test
    void deleteUserById() throws UserNotFoundException {
        Long userId = 1L;
        given(userTestRepository.existsById(userId)).willReturn(true);
        userTestService.deleteUserById(userId);
        verify(userTestRepository).deleteById(userId);
    }
}
