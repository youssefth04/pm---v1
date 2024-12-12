package com.ysf.pm.models;

import lombok.Data;

@Data
public class UserEntity {
	

	private String id;
	private String nom;
	private String email;
	private Roles role;

}
