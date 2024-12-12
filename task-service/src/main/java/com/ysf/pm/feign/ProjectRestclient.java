package com.ysf.pm.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ysf.pm.models.ProjectEntity;

@FeignClient("project-service")
public interface ProjectRestclient {
	
	@GetMapping("/api/projects/{projectId}")
	public ProjectEntity findProjectbyid(@PathVariable String projectId);
	
	
}
