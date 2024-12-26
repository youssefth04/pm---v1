package com.ysf.pm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ysf.pm.entities.ProjectEntity;


@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, String>{


	ProjectEntity findByUserIds(List<String> userIds);
	ProjectEntity findByTaskIds(List<Long> taskIds);
	
	@Query("SELECT p FROM ProjectEntity p WHERE :userId MEMBER OF p.userIds")
	List<ProjectEntity> findAllByUserId(@Param("userId") String userId);
}
