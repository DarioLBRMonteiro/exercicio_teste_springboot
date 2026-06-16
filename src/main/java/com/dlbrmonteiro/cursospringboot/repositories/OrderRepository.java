package com.dlbrmonteiro.cursospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dlbrmonteiro.cursospringboot.entities.Order;

@Repository //opcional
public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
