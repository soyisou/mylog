package com.acjava.mytool.trace.supprot.mq.rocketmq;

import com.acjava.mytool.core.util.StringUtil;
import com.acjava.mytool.trace.util.TraceUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.hook.ConsumeMessageContext;
import org.apache.rocketmq.client.hook.ConsumeMessageHook;
import org.apache.rocketmq.common.message.MessageExt;
import java.util.List;

/**
 * @author loujm
 */

public class TraceConsumeMessageHook implements ConsumeMessageHook {

    @Override
    public String hookName() {
        return "traceConsumeMessageHook";
    }

    @Override
    public void consumeMessageBefore(ConsumeMessageContext context) {
        String traceId = null;
        List<MessageExt> msgList = context.getMsgList();
        if(CollectionUtils.isNotEmpty(msgList)){
            traceId = msgList.get(0).getProperties().get("traceId");
        }
        // 如果生产者没传traceId，则默认用MsgId作为traceId
        if (StringUtil.isEmpty(traceId)) {
            traceId = msgList.get(0).getMsgId();
        }
        TraceUtil.setTraceId(traceId);
    }

    @Override
    public void consumeMessageAfter(ConsumeMessageContext context) {
        TraceUtil.clear();
    }
}
