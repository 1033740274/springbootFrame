package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserDto;
import com.example.demo.service.IUserService;

/**
 * 用户controller
 * @author 10337
 *
 */
// @CrossOrigin 解决跨域问题
@CrossOrigin
@RestController
@RequestMapping("/")
public class SpringBootController {
	
	@Autowired
	private IUserService userService;

	/**
	 * @return
	 * 默认页面的信息
	 */
	@RequestMapping("")
	private String helloSpringBoot() {
		return "helloSpringBoot";
	}
	
	/**
	 * 查询用户列表
	 * @return List<UserDto> users
	 */
	@RequestMapping("getUser") 
	private List<UserDto> getUser() {
		
		List<UserDto> users = new ArrayList<>();
		try {
			users = userService.getUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	/**
	 * 添加用户
	 * @return map
	 */
	@RequestMapping("addUser") 
	private Map<String ,Object> addUser() {
		
		Map<String , Object> map = new HashMap<String, Object>();
		try {
			userService.addUser();
			map.put("success", "true");
			map.put("message", "插入成功");
		} catch (Exception e) {
			map.put("success", "false");
			map.put("message", e.getMessage());
		}
		return map;
	}

}
