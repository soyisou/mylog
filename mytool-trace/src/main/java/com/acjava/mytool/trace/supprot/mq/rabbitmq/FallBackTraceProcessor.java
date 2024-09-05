package com.acjava.mytool.trace.supprot.mq.rabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;

/**
 * @author loujm
 */
public class FallBackTraceProcessor implements TraceMessagePostProcessor {
    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        System.out.println("requeue... 需要回退span?");
        // todo span要用？
        return message;
    }
}
