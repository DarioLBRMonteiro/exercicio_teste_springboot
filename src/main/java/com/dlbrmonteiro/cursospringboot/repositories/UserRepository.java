package com.dlbrmonteiro.cursospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dlbrmonteiro.cursospringboot.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
