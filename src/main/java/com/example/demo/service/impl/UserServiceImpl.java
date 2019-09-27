package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.BussinessException;
import com.example.demo.model.UserDto;
import com.example.demo.service.IUserService;

/**
 *	service层捕获异常后抛出，由controller层进行处理，也方便触发事务
 * @author 10337
 */
@Service
//显示指明回滚的异常类型，不指明时抛出异常不会回滚
@Transactional(rollbackFor= {Exception.class})
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;

	/* 
	 * @see com.example.demo.service.IUserService#getUser()
	 */
	@Override
	public List<UserDto>  getUser() throws Exception {
		List<UserDto> list = new ArrayList<UserDto>();
		try {
			 List<UserEntity> users = userMapper.getUser();
			 list = EntityList2DtoList(users,list);
		} catch (Exception e) {
			throw new BussinessException(e.getMessage(), e.getCause());
		}
		return list;
	}
	
	/**
	 * @see com.example.demo.service.IUserService#addUser()
	 */
	@Override
	@SuppressWarnings("null")
	public void addUser() throws Exception {
		
		try {
			userMapper.addUser();		
			String a = null;
			a.indexOf(0);
		} catch (NullPointerException e) {
			throw new BussinessException("空指针异常");
		} catch (Exception e) {			
			throw new BussinessException("插入异常");
		}
	}
	
	/**
	 * List<UserEntity>转为List<UserDto>
	 * @param fromList
	 * @param toList
	 * @return 转换后的toList
	 * @throws Exception
	 */
	private List<UserDto> EntityList2DtoList(List<UserEntity> fromList,List<UserDto> toList) throws Exception {
		for (UserEntity userEntity : fromList) {
			UserDto userDto = new UserDto();
			try {
				BeanUtils.copyProperties(userEntity, userDto);
			} catch (Exception e) {
				throw new BussinessException("用户" + userEntity.getUsername() + "转换失败！" + e.getMessage());  
			}
			toList.add(userDto);
		}
		return toList;
	}

}
