package org.ipph.spider.jms;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfiguration {
    public static final String PATENT_FEE_QUEUE_NAME = "activemq_patent_fee_queue";
    
    @Bean(name=JmsConfiguration.PATENT_FEE_QUEUE_NAME)
    public Queue patentFeeQueue() {
        return new ActiveMQQueue(PATENT_FEE_QUEUE_NAME);
    }
}
