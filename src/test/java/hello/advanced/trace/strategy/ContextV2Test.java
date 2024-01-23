package hello.advanced.trace.strategy;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.strategy.code.strategy.ContextV2;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogicV1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogicV2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2Test {

	/**
	 * 전략 패턴 적용
	 */
	@Test
	void strategyV1() {
		ContextV2 context = new ContextV2();
		context.execute(new StrategyLogicV1());
		context.execute(new StrategyLogicV2());

	}

	/**
	 * 전략 패턴 익명 내부 클래스
	 */
	@Test
	void strategyV2() {
		ContextV2 context = new ContextV2();
		context.execute(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		});
		context.execute(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		});

	}

	/**
	 * 전략 패턴 익명 내부 클래스2, 람다
	 */
	@Test
	void strategyV3() {
		ContextV2 context = new ContextV2();
		context.execute(() -> log.info("비즈니스 로직1 실행"));
		context.execute(() -> log.info("비즈니스 로직2 실행"));

	}

}
