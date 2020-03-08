package com.ur.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ur.domain.Store;

@Repository
public interface PlaceRepository extends JpaRepository<Store, Long> {	
	@Query("select s from User u join u.stores s where u.id = :userId and s.status in (0, 1) or (s.status = -1 and s.lastAction <= :beforeTwoHours)")
	List<Store> getAllUnremovedStoresWhithTime(
			@Param("userId") Long userId,
			@Param("beforeTwoHours") Date twoHourBack
	);
}
