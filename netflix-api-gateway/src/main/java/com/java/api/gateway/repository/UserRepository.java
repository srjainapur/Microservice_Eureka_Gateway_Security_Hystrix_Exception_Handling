package com.java.api.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.api.gateway.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	User findByEmail(String email);
}
