package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;


// Dao
// 자동으로 bean으로 등록이 된다 // @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer>{
	// JPA naming 전략
	// select * from user where username=? and password=?;
	// User findByUsernameAndPassword(String username, String password);
	
	//	@Query(value="select * from user where username=? and password=?", nativeQuery = true)
	//	User login(String username, String password);
	
}
