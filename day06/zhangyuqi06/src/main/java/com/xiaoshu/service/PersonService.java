package com.xiaoshu.service;

import java.util.List;

import javax.xml.registry.infomodel.PersonName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.ExpressCompanyMapper;
import com.xiaoshu.dao.ExpressPersonMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.ExpressCompany;
import com.xiaoshu.entity.ExpressPerson;
import com.xiaoshu.entity.ExpressPersonExample;
import com.xiaoshu.entity.ExpressPersonExample.Criteria;
import com.xiaoshu.entity.PersonVo;
import com.xiaoshu.entity.User;


@Service
public class PersonService {

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
	}
*/
	@Autowired
	private ExpressPersonMapper expressPersonMapper;
	public PageInfo<PersonVo> findUserPage(PersonVo personVo, int pageNum, int pageSize, String ordername, String order) {
		PageHelper.startPage(pageNum, pageSize);
		ordername = StringUtil.isNotEmpty(ordername)?ordername:"userid";
		order = StringUtil.isNotEmpty(order)?order:"desc";
	
		List<PersonVo> userList = expressPersonMapper.findPage(personVo);
		PageInfo<PersonVo> pageInfo = new PageInfo<PersonVo>(userList);
		return pageInfo;
	}
	@Autowired
	private ExpressCompanyMapper expressCompanyMapper;
	public List<ExpressCompany> findById() {
		// TODO Auto-generated method stub
		return expressCompanyMapper.selectAll();
	}
	// 新增
	public void addUser(ExpressPerson t) throws Exception {
		expressPersonMapper.insert(t);
	};

	// 修改
	public void updateUser(ExpressPerson t) throws Exception {
		expressPersonMapper.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		expressPersonMapper.deleteByPrimaryKey(id);
	};

	// 通过用户名判断是否存在，（新增时不能重名）
	public ExpressPerson existUserWithUserName(String userName) throws Exception {
		ExpressPersonExample example=new ExpressPersonExample();
		Criteria criteria = example.createCriteria();
		criteria.andExpressNameEqualTo(userName);
		List<ExpressPerson> userList = expressPersonMapper.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	}
	public Integer findNameById(String cname) {
		ExpressCompany company = new ExpressCompany();
		company.setExpressName(cname);
		ExpressCompany company2 = expressCompanyMapper.selectOne(company);
		
		return company2.getId();
	}
	public List<PersonVo> findPage(PersonVo personVo) {
		// TODO Auto-generated method stub
		return expressPersonMapper.findPage(personVo);
	}
	public List<PersonVo> findEcharts() {
		// TODO Auto-generated method stub
		return expressPersonMapper.findEcharts();
	}
	
}
