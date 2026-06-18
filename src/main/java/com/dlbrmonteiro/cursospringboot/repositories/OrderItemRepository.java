package com.dlbrmonteiro.cursospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dlbrmonteiro.cursospringboot.entities.OrderItem;
import com.dlbrmonteiro.cursospringboot.entities.pk.OrderItemPK;

@Repository //opcional
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{
	
}
