package com.bookRealm.api_v1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookRealm.api_v1.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);
	User findByUserName(String username);

}
