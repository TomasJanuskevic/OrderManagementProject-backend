package com.OrderManagement.repository;

import com.OrderManagement.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userTestRepository;

    @Test
    void findByUserName() {
        String userName = "Bob";
        User actualUser = new User(userName, "password");
        userTestRepository.save(actualUser);

        User expectedUser = userTestRepository.findByUserName(userName);
        assertThat(actualUser).isEqualTo(expectedUser);
    }
}
