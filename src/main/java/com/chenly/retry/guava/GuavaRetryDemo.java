package com.chenly.retry.guava;

import com.github.rholder.retry.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author chenly
 * @create 2021-03-21 15:17
 */
public class GuavaRetryDemo {
	private static final Logger LOGGER = LoggerFactory.getLogger(GuavaRetryDemo.class);

	public static void main(String[] args) {
		//定义重试机制
		Retryer<Double> retryer = RetryerBuilder.<Double>newBuilder()
				//retryIf 重试条件
				.retryIfException()
				.retryIfResult(Objects::isNull)
				.retryIfException(throwable -> Objects.equals(throwable, new Exception()))
				.retryIfExceptionOfType(Exception.class)
				.retryIfRuntimeException()

				//时间限制 : 某次请求不得超过2s  该方法因SimpleTimeLimiter构造函数变更已失效无法使用
				//.withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))
				//停止策略 : 尝试请求6次
				.withStopStrategy(StopStrategies.stopAfterAttempt(6))
				//等待策略：每次请求间隔1s
				.withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
				//如何实现重试的时间间隔，默认的阻塞策略：线程睡眠
				.withBlockStrategy(BlockStrategies.threadSleepStrategy())
				//自定义重试监听器,在call方法调用结束执行
				.withRetryListener(new CustomRetryListener())
				.build();

		//定义请求实现
		Callable<Double> callable = () -> {
			Calculator calculator = new Calculator();
			return calculator.divide(1, 0);

		};


		//利用重试器调用请求
		try {
			retryer.call(callable);
		} catch (RetryException | ExecutionException e) {
			LOGGER.error("重试失败，出现异常", e);
		}
	}
}
