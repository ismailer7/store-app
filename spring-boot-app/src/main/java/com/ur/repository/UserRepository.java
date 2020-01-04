package com.ur.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ur.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findUserByUsernameAndPassword(String email, String password);
	Optional<User> findByUsername(String username);
	
}
