package com.chenly.retry;

import com.chenly.retry.spring.SpringRetry;
import com.chenly.retry.spring.TransferService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;

@EnableRetry
@SpringBootApplication
@EnableTransactionManagement
public class RetryApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(RetryApplication.class, args);
		SpringRetry bean = applicationContext.getBean(SpringRetry.class);
		bean.test2(-1, 0);

	/*	TransferService bean1 = applicationContext.getBean(TransferService.class);
		bean1.transfer("","", BigDecimal.ONE);*/
	}

}
