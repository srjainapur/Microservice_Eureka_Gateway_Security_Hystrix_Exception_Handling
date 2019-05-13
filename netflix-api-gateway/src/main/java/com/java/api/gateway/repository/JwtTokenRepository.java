package com.java.api.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.api.gateway.bean.JwtToken;
import com.java.api.gateway.bean.User;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, String> {

}
