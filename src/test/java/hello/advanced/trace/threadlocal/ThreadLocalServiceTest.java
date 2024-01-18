package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {
    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    public void filed() {
        log.info("main start");
        Runnable userA = () -> {
            threadLocalService.logic("userA");
        };

        Runnable userB = () -> {
            threadLocalService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread - A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread - B");

        threadA.start();
//        sleep(2000); //동시성 문제 발생 X
        sleep(100); //sleep을 굉장히 짧게 줬을 때 동시성 문제가 발생한다.
        threadB.start();

        sleep(3000);
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
