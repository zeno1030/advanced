package hello.advanced.trace.strategy;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogicV1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogicV2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1Test {
	@Test
	void strategyV0() {
		logic1();
		logic2();
	}

	private void logic1() {
		long startTime = System.currentTimeMillis();
		//비즈니스 로직 실행
		log.info("비즈니스 로직1 실행");
		//비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime = {}", resultTime);
	}

	private void logic2() {
		long startTime = System.currentTimeMillis();
		//비즈니스 로직 실행
		log.info("비즈니스 로직2 실행");
		//비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime = {}", resultTime);
	}

	/**
	 * 전략패턴사용
	 */
	@Test
	void strategyV1() {
		StrategyLogicV1 strategyLogicV1 = new StrategyLogicV1();
		ContextV1 context1 = new ContextV1(strategyLogicV1);
		context1.execute();
		StrategyLogicV2 strategyLogicV2 = new StrategyLogicV2();
		ContextV1 context2 = new ContextV1(strategyLogicV2);
		context2.execute();
	}

	@Test
	void strategyV2() {
		Strategy strategyLogic1 =  new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		};
		ContextV1 contextV1 = new ContextV1(strategyLogic1);
		log.info("strategyLogic1 = {}", strategyLogic1.getClass());
		contextV1.execute();
		Strategy strategyLogic2 =  new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		};
		ContextV1 contextV2 = new ContextV1(strategyLogic2);
		log.info("strategyLogic2 = {}", strategyLogic2.getClass());
		contextV2.execute();
	}

	@Test
	void strategyV3() {
		ContextV1 contextV1 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		});
		contextV1.execute();
		ContextV1 contextV2 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		});
		contextV2.execute();
	}

	@Test
	void strategyV4() {
		ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
		contextV1.execute();

		ContextV1 contextV2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
		contextV2.execute();
	}
}
