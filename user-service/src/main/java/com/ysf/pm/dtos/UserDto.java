package com.ysf.pm.dtos;

import com.ysf.pm.enums.Roles;

import lombok.Data;

@Data
public class UserDto {
	
	private String id;
	private String nom;
	private String email;
	private Roles role;

	
}
