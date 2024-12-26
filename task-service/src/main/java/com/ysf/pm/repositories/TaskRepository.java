package com.ysf.pm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ysf.pm.entities.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long>{
	
	public  List<TaskEntity> findAllById(Iterable<Long> ids);
	
	@Query("select t from TaskEntity t where t.userId= :userId ")
	public List<TaskEntity> findAllByUserId(@Param("userId") String userId);
	
	
	public List<TaskEntity> findByProjectId(String projectId);
}
