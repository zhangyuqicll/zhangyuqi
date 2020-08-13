package com.xiaoshu.controller;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshu.entity.Major;

public class MyMessageListener implements MessageListener{
@Autowired
private RedisTemplate redisTemplate;
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage textMessage=(TextMessage) message;
		try {
			String text = textMessage.getText();
			Major major = JSONObject.parseObject(text,Major.class);
			System.out.println("接收到的数据为----------------"+major);
			redisTemplate.boundHashOps("major").put(major.getMdname(), major.getMdId());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
