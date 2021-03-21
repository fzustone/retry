package com.chenly.retry;

import com.chenly.retry.spring.User;
import com.chenly.retry.spring.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class RetryApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private UserRepository userRepository;

	@Test
	public void saveTest() throws Exception {
		User user = new User();
		user.setName("郑龙飞");
		user.setUrl("http://merryyou.cn");
		User result = userRepository.save(user);
		log.info(result.toString());
	}

	/*@Test
	public void findOneTest() throws Exception{
		User user = userRepository.findOne(1l);
		log.info(user.toString());

	}*/


}
