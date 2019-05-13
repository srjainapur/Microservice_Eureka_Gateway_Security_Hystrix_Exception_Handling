package com.java.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.account.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByEmail(String email);
	public User findByActive(Integer isActive);
}
