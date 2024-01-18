package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class HelloTraceV2Test {

    @Test
    void begin_end() {
        LogTrace trace = new LogTrace();
        TraceStatus status = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status);
    }
    @Test
    void begin_exception() {
        LogTrace trace = new LogTrace();
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }


}
