package com.ysf.pm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ysf.pm.entities.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

	
	
	public  List<UserEntity> findAllById(Iterable<String> ids);
	
	
}
