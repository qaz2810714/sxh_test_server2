package com.yongqiang.wms.service;

import com.yongqiang.wms.mapper.UserMapper;
import com.yongqiang.wms.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kai.chen
 *	权限人员相关实现类
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

//	@Transactional
//	public int insertUser(User user) {
//		int a =	userMapper.insert(user);
//		//添加用户对应的角色
//		if(user.getRoleIds()!=null&&user.getRoleIds().size()>0){
//			userMapper.addBatchRolesOfUser(user);
//		}
//		return a;
//	}

//	@Transactional
//	public int updateUser(User user) {
//		int a = userMapper.updateByPrimaryKey(user);
//		 //删除所有用户角色关系
//		userMapper.deleteAllRolesOfUser(user.getId());
//    	//添加新的用户角色关系
//		if(user.getRoleIds()!=null&&user.getRoleIds().size()>0){
//			userMapper.addBatchRolesOfUser(user);
//		}
//		return a;
//	}


	
//	@Override
//	public void updatePassword(User user) {
//		String redisKey = RedisKeyConstant.PREFIX_PASSWORD_LOCK + BizContext.getAccountId() + "_" + user.getLoginName();
//		jedisTemplate.del(redisKey);
//		userMapper.updatePassword(user);
//	}
	
//	@Override
//	public Page<User> getDataByPage(User user) {
//		Pageable pageable = new Pageable(user.getPage(), user.getSize());
//		Page<User> page = userMapper.selectUsersByPage(user, pageable);
//		return page;
//	}

//	public List<User> selectUsersByDeptIds(List<Long> deptIds) {
//		return userMapper.selectUsersByDeptIds(deptIds);
//	}

	public User selectUserByLogin(User user) {
		return userMapper.selectUserByLogin(user);
	}

//	@Override
//	public boolean checkUnique(User user) {
//		List<User> userList = userMapper.selectUserByLoginName(user);
//		return userList.size()>0?true:false;
//	}

//	@Override
//	public List<User> getAllValidUser() {
//		return userMapper.getAllValidUser();
//	}

//	@Override
//	public boolean checkMaxNum(User user) {
//		Boolean isOk = true;
//		WhsOrg whsOrg = whsOrgMapper.queryWhsOrgById(user.getAccountId());
//		int nowNum = userMapper.countAccountMaxNum(user.getAccountId());
//		int max = whsOrg==null?0:whsOrg.getAccountMaxNum();
//		if(nowNum< max){
//			isOk = false;
//		}
//		return isOk;
//	}

//	@Override
//	public User selectUserById(Long userId) {
//		return userMapper.selectByPrimaryKey(userId);
//	}
}
