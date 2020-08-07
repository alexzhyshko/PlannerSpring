package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.zhyshko.exception.UserNotFoundException;
import com.zhyshko.model.User;
import com.zhyshko.repository.UserRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> getAllUsers(){
		List<User> result = new ArrayList<>();
		for(User user : userRepository.findAll()) {
			result.add(user);
		}
		return result;
	}
	
	public User getUserById(UUID id){
		return userRepository.findById(id).orElse(null);
	}
	
	public void deleteUser(UUID id){
		userRepository.deleteById(id);
	}
	
	public User updateUser(User user){
		return userRepository.save(user);
	}
	
	
}
