package com.acjava.mytool.trace.supprot.async;

import org.slf4j.MDC;

import java.util.concurrent.Callable;

/**
 * @author loujm
 */
public class TraceCallable<V> implements Callable<V> {

    private final String traceId;
    private final Callable<V> delegate;

    public TraceCallable(Callable<V> delegate, String traceId) {
        this.delegate = delegate;
        this.traceId = traceId;
    }

    @Override
    public V call() throws Exception {
        MDC.put("traceId", traceId);
        try{
            return this.delegate.call();
        } catch (Exception | Error e) {
            System.err.println("将错误记录到span中" + e);
            throw e;
        } finally {
            MDC.clear();
        }
    }
}
