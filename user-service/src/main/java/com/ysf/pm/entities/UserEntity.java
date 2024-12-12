package com.ysf.pm.entities;

import com.ysf.pm.enums.Roles;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserEntity {
	@Id
	private String id;
	private String nom;
	private String email;
	@Enumerated(EnumType.STRING)
	private Roles role;
}
