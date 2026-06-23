package com.dlbrmonteiro.cursospringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dlbrmonteiro.cursospringboot.entities.Category;
import com.dlbrmonteiro.cursospringboot.repositories.CategoryRepository;
import com.dlbrmonteiro.cursospringboot.services.exceptions.DatabaseException;
import com.dlbrmonteiro.cursospringboot.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public List<Category> findAll() {
		return repository.findAll();
	}

	public Category findById(Long id) {
		// Optional<Category> obj = repository.findById(id);
		// return obj.get();

//		Category category = repository.findById(id).orElseThrow();
//		return category;

		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Category insert(Category obj) {
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

	public Category update(Long id, Category category) {
		try {
			Category entity = repository.getReferenceById(id);
			updateData(entity, category);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());
	}
}
