package com.dlbrmonteiro.cursospringboot.services;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlbrmonteiro.cursospringboot.entities.Category;
import com.dlbrmonteiro.cursospringboot.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;

	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		//Optional<Category> obj = repository.findById(id);
		// return obj.get();
		
		Category category = repository.findById(id).orElseThrow();
		return category;
	}
}
