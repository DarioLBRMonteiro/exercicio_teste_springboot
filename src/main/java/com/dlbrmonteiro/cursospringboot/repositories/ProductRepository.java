package com.dlbrmonteiro.cursospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dlbrmonteiro.cursospringboot.entities.Product;

@Repository //opcional
public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
