package com.chenly.retry.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author chenly
 * @create 2021-03-21 16:26
 */
@Service
public class SpringRetry {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringRetry.class);

	@Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 2000L, multiplier = 2, random = false), label = "小陈小陈")
	public double test1(double a, double b) throws Exception {
		//LOGGER.info("Here with label: " + RetrySynchronizationManager.getContext().getAttribute(RetryContext.NAME));

		LOGGER.info("" + LocalDateTime.now());
		if (b == 0) {
			LOGGER.error("被除数不能为0");
			throw new Exception("被除数是0");
		}
		return a / b;
	}

	@Recover
	public double recover1(RuntimeException ex, Double a, Double b) {
		LOGGER.info("执行recover1方法");
		throw ex;
	}

	@Recover
	public Double recover22(RuntimeException ex, Double a, Double b) {
		LOGGER.info("执行recover2方法");
		throw ex;
	}

	@Retryable(recover = "recover22", value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 2000L, multiplier = 1.5))
	public double test2(double a, double b) throws Exception {

		if (b == 0) {
			LOGGER.error("second method:被除数不能为0");
			throw new RuntimeException("second method:被除数是0");
		}
		return a / b;
	}

}
