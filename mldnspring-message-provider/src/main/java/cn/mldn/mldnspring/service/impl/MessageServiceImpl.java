package cn.mldn.mldnspring.service.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import cn.mldn.mldnspring.service.IMessageService;

@Component
public class MessageServiceImpl implements IMessageService {
	@Autowired
	private JmsTemplate jmsTemplate ;	// Spring提供的JMS消息操作模版
	@Autowired
	private Destination destination ; 	// 消息发送的目的地
	@Override
	public void send(String msg) { 
		this.jmsTemplate.send(this.destination, new MessageCreator(){
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);	// 发送文本消息
			}
		});

	}

}
