package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.UserEntity;

/**
 * mapper接口
 * @author 10337
 *
 */
@Mapper
public interface UserMapper {

	@Select({"select * from \"user\""})
	List<UserEntity> getUser();
	
	void addUser();
}
