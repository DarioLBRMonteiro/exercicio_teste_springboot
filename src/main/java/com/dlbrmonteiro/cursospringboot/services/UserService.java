package com.dlbrmonteiro.cursospringboot.services;

import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dlbrmonteiro.cursospringboot.entities.User;
import com.dlbrmonteiro.cursospringboot.repositories.UserRepository;
import com.dlbrmonteiro.cursospringboot.services.exceptions.DatabaseException;
import com.dlbrmonteiro.cursospringboot.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
//		Optional<User> obj = repository.findById(id);
//		return obj.get();
		
//		User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
//		return user;
				
		Optional<User> obj = repository.findById(id);	
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));				
	}
	
	public User insert(User obj) {
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
	
	public User update(Long id, User user) {
		User entity = repository.getReferenceById(id);
		updateData(entity,user);
		return repository.save(entity);
	}
	
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
