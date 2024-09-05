package com.acjava.mytool.trace.supprot.mq.rocketmq;

import com.acjava.mytool.trace.util.TraceUtil;
import org.apache.rocketmq.client.hook.SendMessageContext;
import org.apache.rocketmq.client.hook.SendMessageHook;

/**
 * @author loujm
 */
public class TraceSendMessageHook implements SendMessageHook {

    @Override
    public String hookName() {
        return "traceSendMessageHook";
    }

    @Override
    public void sendMessageBefore(SendMessageContext sendMessageContext) {
        String traceId = TraceUtil.getTraceId();
        sendMessageContext.getMessage().putUserProperty("traceId", traceId);
    }

    /**
     * 发送消息，无需手动清处，一般是方法结束之后才会清理的。如：函数里面发一个消息，并不代表你这个消息后面就没有其他事情要做了
     */
    @Override
    public void sendMessageAfter(SendMessageContext sendMessageContext) {
        TraceUtil.clear();
    }
}
