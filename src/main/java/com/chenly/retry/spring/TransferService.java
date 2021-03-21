package com.chenly.retry.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenly
 * @create 2021-03-21 17:29
 */
@Component
public class TransferService {
	private AtomicInteger count = new AtomicInteger(0);
	private static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);

	@Retryable(include = ConcurrencyFailureException.class, stateful = false)
	@Transactional
	public void transfer(String fromAccountNo, String toAccountNo, BigDecimal amount) {
		// do transaction
		LOGGER.info("开始执行");
		int i = count.addAndGet(1);
		// rollback
		if (i % 3 != 0) {
			throw new OptimisticLockingFailureException("data already changed");
		}

		LOGGER.info("执行完了");
	}
}
