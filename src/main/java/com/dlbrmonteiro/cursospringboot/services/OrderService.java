package com.dlbrmonteiro.cursospringboot.services;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlbrmonteiro.cursospringboot.entities.Order;
import com.dlbrmonteiro.cursospringboot.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;

	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		//Optional<Order> obj = repository.findById(id);
		// return obj.get();
		
		Order order = repository.findById(id).orElseThrow();
		return order;
	}
}
