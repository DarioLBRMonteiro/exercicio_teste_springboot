package com.dlbrmonteiro.cursospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dlbrmonteiro.cursospringboot.entities.User;

@Repository //opcional
public interface UserRepository extends JpaRepository<User, Long>{
	
}
