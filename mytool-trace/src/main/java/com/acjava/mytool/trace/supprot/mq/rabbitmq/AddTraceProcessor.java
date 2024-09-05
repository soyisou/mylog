package com.acjava.mytool.trace.supprot.mq.rabbitmq;

import com.acjava.mytool.trace.util.TraceUtil;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 * @author loujm
 */
public class AddTraceProcessor implements TraceMessagePostProcessor {
    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        MessageProperties properties = message.getMessageProperties();
        String traceId = TraceUtil.startTrace();
        properties.getHeaders().put(TraceUtil.TRACE_ID, traceId);
        return message;
    }
}
