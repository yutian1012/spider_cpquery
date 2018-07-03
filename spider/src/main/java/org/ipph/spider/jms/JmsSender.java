package org.ipph.spider.jms;

import javax.jms.Queue;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsSender implements ApplicationContextAware{
    @Autowired
    private JmsMessagingTemplate jmsTemplate;
    
    private ApplicationContext context;
    
    public void sendByQueue(String message,String queueName) {
    	if(null==context||null==queueName) {
    		
    		return;
    	}
    	Queue queue=(Queue) context.getBean(queueName);
        
    	this.jmsTemplate.convertAndSend(queue, message);
    }

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context=context;
	}
    
}
