package com.xiaoshu.controller;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;


import com.xiaoshu.entity.Bank;
import com.xiaoshu.service.PersonService;


public class MyMessageListener implements MessageListener{
@Autowired
private PersonService personService;
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage textMessage=(TextMessage) message;
		try {
			String id =textMessage.getText();
			
			List<Bank> findBank = personService.findBank();
			for (Bank bank : findBank) {
					if(bank.getbId().toString().equals(id)){
						System.out.println("Mq接受到的消息为----------------------------------"+bank);
					}
				
			}
		
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
