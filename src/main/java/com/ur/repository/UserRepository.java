package com.ur.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ur.domain.Store;
import com.ur.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findUserByUsernameAndPassword(String email, String password);
	
	Optional<User> findByUsername(String username);
	
	@Query("select s from User u join u.stores s where u.id = :userId and s.status = 1")
	List<Store> getAllPreferredStores(
			@Param("userId") Long userId
	);
	@Query("select s from User u join u.stores s where u.id = :userId and s.id = :storeId")
	Store getStoreById(@Param("userId") Long userId, @Param("storeId") Long storeId);
}
