package com.xiaoshu.controller;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiaoshu.dao.SchoolMapper;
import com.xiaoshu.entity.School;

public class MyMessageListener implements MessageListener {
@Autowired
	private SchoolMapper schoolMapper;
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage=(TextMessage) message;
		try {
			String sname = textMessage.getText();
			int depid = Integer.parseInt(sname);
			School school = schoolMapper.selectByPrimaryKey(depid);
			System.out.println("接受到的部门信息为为="+school);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
