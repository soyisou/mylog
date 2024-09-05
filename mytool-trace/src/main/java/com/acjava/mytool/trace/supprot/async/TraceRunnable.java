package com.acjava.mytool.trace.supprot.async;

import org.slf4j.MDC;

/**
 * @author loujm
 */
public class TraceRunnable implements Runnable{

    private final String traceId;
    private final Runnable delegate;

    public TraceRunnable(Runnable delegate, String traceId) {
        this.delegate = delegate;
        this.traceId = traceId;
    }

    @Override
    public void run() {
        MDC.put("traceId", traceId);
        try {
            this.delegate.run();
        } catch (Exception | Error e) {
            System.err.println("将错误记录到span中" + e);
        } finally {
            MDC.clear();
        }
    }
}
