package com.chenly.retry.guava;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenly
 * @create 2021-03-21 16:07
 */
public class CustomRetryListener implements RetryListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomRetryListener.class);

	@Override
	public <V> void onRetry(Attempt<V> attempt) {

		// 第几次重试,(注意:第一次重试其实是第一次调用)
		LOGGER.info("retry time : [{}]", attempt.getAttemptNumber());

		// 距离第一次重试的延迟
		LOGGER.info("retry delay : [{}]", attempt.getDelaySinceFirstAttempt());

		// 是否因异常终止
		if (attempt.hasException()) {
			LOGGER.info("causeBy:", attempt.getExceptionCause());
		}
		// 正常返回时的结果
		if (attempt.hasResult()) {
			LOGGER.info("result={}", attempt.getResult());
		}

	}
}
