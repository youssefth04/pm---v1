package com.ysf.pm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ysf.pm.entities.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long>{
	
	public  List<TaskEntity> findAllById(Iterable<Long> ids);
	
}
