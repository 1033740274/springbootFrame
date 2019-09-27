package com.example.demo.model;

import java.util.UUID;

import lombok.Data;

@Data
public final class UserDto {
	private String username;
	private String password;
	private String id = UUID.randomUUID().toString().replace("-", "");

}
