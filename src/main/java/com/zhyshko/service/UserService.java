package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.model.User;
import com.zhyshko.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	
	@Transactional
	public com.zhyshko.model.User addUser(com.zhyshko.model.User user) {
		return userRepository.save(user);
	}
	
	
	@Transactional
	public List<com.zhyshko.model.User> getAllUsers(){
		List<com.zhyshko.model.User> result = new ArrayList<>();
		for(User user : userRepository.findAll()) {
			result.add(user);
		}
		return result;
	}
	
	
	
	@Transactional
	public com.zhyshko.model.User getUserById(UUID id){
		return userRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public com.zhyshko.model.User getUserByUsername(String username){
		return userRepository.findByUsername(username).orElse(null);
	}
	
	
	@Transactional
	public void deleteUser(UUID id){
		userRepository.deleteById(id);
	}
	
	
	@Transactional
	public com.zhyshko.model.User updateUser(com.zhyshko.model.User user){
		return addUser(user);
	}
	
	
}
