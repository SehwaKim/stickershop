package com.boot.stickershop.repository;

import com.boot.stickershop.domain.User;
import com.boot.stickershop.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	@Autowired
	UserRepository userRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testUser(){
		User user = new User();
		user.setRegtime(LocalDateTime.now());
		user.setPassword("1234");
		user.setName("kim");
		user.setEmail("u@gmail.com");
		user = userRepository.save(user);
		System.out.println(user);
	}

}
