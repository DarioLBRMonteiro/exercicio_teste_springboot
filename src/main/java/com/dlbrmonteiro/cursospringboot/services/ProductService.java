package com.dlbrmonteiro.cursospringboot.services;

import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlbrmonteiro.cursospringboot.entities.Category;
import com.dlbrmonteiro.cursospringboot.entities.Product;
import com.dlbrmonteiro.cursospringboot.repositories.CategoryRepository;
import com.dlbrmonteiro.cursospringboot.repositories.ProductRepository;
import com.dlbrmonteiro.cursospringboot.services.exceptions.DatabaseException;
import com.dlbrmonteiro.cursospringboot.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
		//Optional<Product> obj = repository.findById(id);
		// return obj.get();
		
//		Product product = repository.findById(id).orElseThrow();
//		return product;
		
		Optional<Product> obj = productRepository.findById(id);	
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));						
	}
	
	public Product insert(Product obj) {
		return productRepository.save(obj); // codigo de status http 200
	}
	
	public void delete(Long id) {
		try {
//			if (!repository.existsById(id))
//				throw new ResourceNotFoundException(id);
			if (findById(id) != null) 
				productRepository.deleteById(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Product update(Long id, Product product) {
		try {
			Product entity = productRepository.getReferenceById(id);
			updateData(entity,product);
			return productRepository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}		
	}
	
	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setImgUrl(obj.getImgUrl());
	}
	
	@Transactional
    public Product associateCategory(Long productId, Long categoryId) {
        // 1. Busca o produto	
		Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(productId));

        // 2. Busca a categoria
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(categoryId));

        // 3. Associa a categoria ao produto
        product.getCategories().add(category);

        // 4. Salva e retorna o produto atualizado
        return productRepository.save(product);
    }	
	
	
	@Transactional
    public Product associateCategories(Long productId, List<Long> categoriesIds) {
        // 1. Busca o produto	
		Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(productId));

		Category category = new Category();
		
        for (Long catId : categoriesIds) {
            // 2. Busca a categoria
            category = categoryRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException(catId));
        	        	
            // 3. Associa a categoria ao produto
            product.getCategories().add(category);        	
        }

        // 4. Salva e retorna o produto atualizado
        return productRepository.save(product);
    }	
	
}
