package com.ysf.pm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ysf.pm.entities.NotificationEntity;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long>{

	@Query("select n from NotificationEntity n where n.userId = :userId")
	public List<NotificationEntity> finallbyuserId(@Param("userId") String userId);
}
