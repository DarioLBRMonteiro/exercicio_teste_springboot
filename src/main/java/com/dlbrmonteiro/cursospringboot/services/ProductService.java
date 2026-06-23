package com.dlbrmonteiro.cursospringboot.services;

import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dlbrmonteiro.cursospringboot.entities.Product;
import com.dlbrmonteiro.cursospringboot.repositories.ProductRepository;
import com.dlbrmonteiro.cursospringboot.services.exceptions.DatabaseException;
import com.dlbrmonteiro.cursospringboot.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;

	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		//Optional<Product> obj = repository.findById(id);
		// return obj.get();
		
//		Product product = repository.findById(id).orElseThrow();
//		return product;
		
		Optional<Product> obj = repository.findById(id);	
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));						
	}
	
	public Product insert(Product obj) {
		return repository.save(obj); // codigo de status http 200
	}
	
	public void delete(Long id) {
		try {
//			if (!repository.existsById(id))
//				throw new ResourceNotFoundException(id);
			if (findById(id) != null) 
				repository.deleteById(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Product update(Long id, Product product) {
		try {
			Product entity = repository.getReferenceById(id);
			updateData(entity,product);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}		
	}
	
	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setImgUrl(obj.getImgUrl());
	}	
}
