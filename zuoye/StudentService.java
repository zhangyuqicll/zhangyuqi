package com.xiaoshu.service;

import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.SchoolMapper;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.entity.School;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentExample;
import com.xiaoshu.entity.StudentExample.Criteria;
import com.xiaoshu.entity.StudentVo;


@Service
public class StudentService {
/*
	
*/
	@Autowired
	private StudentMapper studentMapper;
	public PageInfo<StudentVo> findUserPage(StudentVo studentVo, int pageNum, int pageSize, String ordername, String order) {
		PageHelper.startPage(pageNum, pageSize);
		ordername = StringUtil.isNotEmpty(ordername)?ordername:"userid";
		order = StringUtil.isNotEmpty(order)?order:"desc";
		StudentExample example=new StudentExample();
		example.setOrderByClause(ordername+" "+order);
		Criteria criteria = example.createCriteria();
		
		List<StudentVo> userList = studentMapper.findPage(studentVo);
		PageInfo<StudentVo> pageInfo = new PageInfo<StudentVo>(userList);
		return pageInfo;
	}
	@Autowired
	private SchoolMapper schoolMapper;
	public List<School> findSchool() {
		// TODO Auto-generated method stub
		return schoolMapper.selectAll();
	}
		@Autowired
		private JmsTemplate jmsTemplate;
		@Autowired
		private Destination queueTextDestination;
	// 新增
	public void addUser(final Student t) throws Exception {
		/*发送员工名称
		jmsTemplate.send(queueTextDestination,new MessageCreator() {	
			@Override
			public Message createMessage(Session session) throws JMSException {		
				return session.createTextMessage(t.getSname());
			}
		});*/
		jmsTemplate.send(queueTextDestination,new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createTextMessage(t.getDepid()+"");
			}
		});
		studentMapper.insert(t);
	};

	// 修改
	public void updateUser(Student t) throws Exception {
		studentMapper.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		studentMapper.deleteByPrimaryKey(id);
	};
	// 通过用户名判断是否存在，（新增时不能重名）
		public Student existUserWithUserName(String userName) throws Exception {
			StudentExample example = new StudentExample();
			Criteria criteria = example.createCriteria();
			criteria.andSnameEqualTo(userName);
			List<Student> userList = studentMapper.selectByExample(example);
			return userList.isEmpty()?null:userList.get(0);
		}

		public List<StudentVo> getEcharts() {
			// TODO Auto-generated method stub
			return studentMapper.getEcharts();
		};


}
