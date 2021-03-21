package com.chenly.retry.guava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务类
 * @author chenly
 * @create 2021-03-21 15:19
 */
public class Calculator {
	private static final Logger LOGGER = LoggerFactory.getLogger(Calculator.class);

	public double divide(double a, double b) throws Exception {
		if (b == 0) {
			LOGGER.error("被除数不能为0");
			throw new Exception("被除数是0");
		}
		return a / b;
	}
}
