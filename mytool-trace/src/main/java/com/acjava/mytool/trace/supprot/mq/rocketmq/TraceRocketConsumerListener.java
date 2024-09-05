package com.acjava.mytool.trace.supprot.mq.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.support.DefaultRocketMQListenerContainer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.util.CollectionUtils;
import java.util.Map;

/**
 * @author loujm
 */
@Slf4j
public class TraceRocketConsumerListener {

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext context = event.getApplicationContext();
        Map<String, DefaultRocketMQListenerContainer> containerMap = context.getBeansOfType(DefaultRocketMQListenerContainer.class);
        if(CollectionUtils.isEmpty(containerMap)){
            return;
        }
        for (DefaultRocketMQListenerContainer container : containerMap.values()) {
            container.getConsumer().getDefaultMQPushConsumerImpl().registerConsumeMessageHook(new TraceConsumeMessageHook());
        }
    }

}
