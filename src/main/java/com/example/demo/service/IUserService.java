package com.example.demo.service;

import java.util.List;

import com.example.demo.model.UserDto;

/**
 *	service接口
 * @author 10337
 */
public interface IUserService {
	
	/**
	 * 查询用户列表
	 * @return userList
	 */
	public List<UserDto>  getUser () throws Exception;

	/**
	 * 添加用户
	 * @throws Exception
	 */
	public void  addUser () throws Exception;
}
