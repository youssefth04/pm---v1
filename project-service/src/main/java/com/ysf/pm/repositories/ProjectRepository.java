package com.ysf.pm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ysf.pm.entities.ProjectEntity;


@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, String>{


	ProjectEntity findByUserIds(List<String> userIds);
	ProjectEntity findByTaskIds(List<Long> taskIds);
}
