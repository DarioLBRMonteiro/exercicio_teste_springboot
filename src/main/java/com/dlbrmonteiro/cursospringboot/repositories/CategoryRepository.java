package com.dlbrmonteiro.cursospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dlbrmonteiro.cursospringboot.entities.Category;

@Repository //opcional
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
