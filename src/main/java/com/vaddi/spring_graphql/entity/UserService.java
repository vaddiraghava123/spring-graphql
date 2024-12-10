package com.vaddi.spring_graphql.entity;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User getUserId(Long userId) {
		 User user = userRepository.findById(userId)
				.orElseThrow(ExceptionHandler::throwResourceNotFoundException );
		return user;
	}
	
	public boolean deleteUser(Long userId) {
		userRepository.findById(userId).orElseThrow(ExceptionHandler::throwResourceNotFoundException );
		userRepository.deleteById(userId);
		return true;
	}
}
