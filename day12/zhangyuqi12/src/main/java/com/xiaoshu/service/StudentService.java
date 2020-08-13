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

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.MajorMapper;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Major;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentExample;
import com.xiaoshu.entity.StudentExample.Criteria;
import com.xiaoshu.entity.StudentVo;
import com.xiaoshu.entity.User;


@Service
public class StudentService {

/*	@Autowired
	UserMapper userMapper;

	// 查询所有
	public List<User> findUser(User t) throws Exception {
		return userMapper.select(t);
	};

	// 数量
	public int countUser(User t) throws Exception {
		return userMapper.selectCount(t);
	};

	// 通过ID查询
	public User findOneUser(Integer id) throws Exception {
		return userMapper.selectByPrimaryKey(id);
	};

	

	// 登录
	public User loginUser(User user) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andPasswordEqualTo(user.getPassword()).andUsernameEqualTo(user.getUsername());
		List<User> userList = userMapper.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};

	

	// 通过角色判断是否存在
	public User existUserWithRoleId(Integer roleId) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleidEqualTo(roleId);
		List<User> userList = userMapper.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	}*/
@Autowired
private StudentMapper studentMapper;
	public PageInfo<StudentVo> findUserPage(StudentVo studentVo, int pageNum, int pageSize, String ordername, String order) {
		PageHelper.startPage(pageNum, pageSize);
		ordername = StringUtil.isNotEmpty(ordername)?ordername:"userid";
		order = StringUtil.isNotEmpty(order)?order:"desc";
	
		List<StudentVo> userList = studentMapper.findPage(studentVo);
		PageInfo<StudentVo> pageInfo = new PageInfo<StudentVo>(userList);
		return pageInfo;
	}
@Autowired
private MajorMapper majorMapper;

	public List<Major> findMajor() {
		// TODO Auto-generated method stub
		return majorMapper.selectAll();
	}
	// 新增
		public void addUser(Student t) throws Exception {
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
			StudentExample example=new StudentExample();
			Criteria criteria = example.createCriteria();
			criteria.andSdnameEqualTo(userName);
			List<Student> userList = studentMapper.selectByExample(example);
			return userList.isEmpty()?null:userList.get(0);
		}
		public List<StudentVo> findPage(StudentVo studentVo) {
			// TODO Auto-generated method stub
			return studentMapper.findPage(studentVo);
		}
		@Autowired
		private JmsTemplate jmsTemplate;
		@Autowired
		private Destination queueTextDestination;
		public void addType(final Major major) {
			
			majorMapper.insert(major);
			jmsTemplate.send(queueTextDestination, new MessageCreator() {
				
				@Override
				public Message createMessage(Session session) throws JMSException {
					String json = JSONObject.toJSONString(major);
					return session.createTextMessage(json);
				}
			});
			
		}
		public List<StudentVo> echartsStudent() {
			// TODO Auto-generated method stub
			return studentMapper.echartsStudent();
		}
		public Integer findById(String cname) {
			Major major = new Major();
			major.setMdname(cname);
			Major selectOne = majorMapper.selectOne(major);
			return selectOne.getMdId();
		};
}
