package com.OrderManagement.service;

import com.OrderManagement.exception.UserNotFoundException;
import com.OrderManagement.model.User;
import com.OrderManagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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
        given(userTestRepository.findById(userId)).willReturn(Optional.of(user));
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
    void updateUser() throws UserNotFoundException {
        User user = new User("Bob", "password");
        given(userTestRepository.existsById(any())).willReturn(true);
        userTestService.updateUser(user);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userTestRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void deleteUserById() throws UserNotFoundException {
        Long userId = 1L;
        given(userTestRepository.existsById(userId)).willReturn(true);
        userTestService.deleteUserById(userId);
        verify(userTestRepository).deleteById(userId);
    }

    @Test
    void willThrowThenDeletedUserIdNotFound() {
        Long userId = 1L;
        given(userTestRepository.existsById(userId)).willReturn(false);
        assertThatThrownBy(() -> userTestService.deleteUserById(userId)).isInstanceOf(UserNotFoundException.class).
                hasMessageContaining("User by id: " + userId + " was not found");
    }

    @Test
    void willThrowThenFindingUserIdNotFound() {
        Long userId = 1L;
        given(userTestRepository.findById(userId)).willReturn(Optional.empty());
        assertThatThrownBy(() -> userTestService.getUserById(userId)).isInstanceOf(UserNotFoundException.class).
                hasMessageContaining("User by id: " + userId + " was not found");
    }
}
