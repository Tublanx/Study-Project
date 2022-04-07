package com.example.security.repository;

import com.example.security.entity.Member;
import org.assertj.core.api.Assertions;
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
    MemberRepository userRepository;

    @Test
    public void 회원생성() {
        Member user = new Member();

        user.setEmail("rlgus03453@naver.com");
        user.setPassword("1234");
        user.setName("이기현");

        Member saveUser = userRepository.save(user);
        Member existUser = testEntityManager.find(Member.class, saveUser.getNo());

        Assertions.assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }

}