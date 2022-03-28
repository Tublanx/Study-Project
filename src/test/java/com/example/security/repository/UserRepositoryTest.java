package com.example.security.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    public void 회원생성() {
        User user = new User();

        user.setEmail("rlgus03453@naver.com");
        user.setPassword("1234");
        user.setName("이기현");

        User saveUser = userRepository.save(user);
        User existUser = testEntityManager.find(User.class, saveUser.getNo());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }

}