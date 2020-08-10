package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.model.Dashboard;
import com.zhyshko.model.User;
import com.zhyshko.repository.UserRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	
	
	@Transactional
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	
	@Transactional
	public List<User> getAllUsers(){
		List<User> result = new ArrayList<>();
		for(User user : userRepository.findAll()) {
			result.add(user);
		}
		return result;
	}
	
	
	
	@Transactional
	public User getUserById(UUID id){
		return userRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public User getUserByUsername(String username){
		return userRepository.findByUsername(username).orElse(null);
	}
	
	
	@Transactional
	public void deleteUser(UUID id){
		userRepository.deleteById(id);
	}
	
	
	@Transactional
	public User updateUser(User user){
		return userRepository.save(user);
	}
	
	
}
