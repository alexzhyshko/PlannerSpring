package com.zhyshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.model.User;
import com.zhyshko.repository.CardRepository;
import com.zhyshko.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private ModelMapper mapper = new ModelMapper();
	
	@Transactional
	public com.zhyshko.dto.User addUser(com.zhyshko.dto.User user) {
		return mapper.map(userRepository.save(mapper.map(user, User.class)), com.zhyshko.dto.User.class);
	}
	
	
	@Transactional
	public List<com.zhyshko.dto.User> getAllUsers(){
		List<com.zhyshko.dto.User> result = new ArrayList<>();
		for(User user : userRepository.findAll()) {
			result.add(mapper.map(user, com.zhyshko.dto.User.class));
		}
		return result;
	}
	
	
	
	@Transactional
	public com.zhyshko.dto.User getUserById(UUID id){
		return mapper.map(userRepository.findById(id).orElse(null), com.zhyshko.dto.User.class);
	}
	
	@Transactional
	public com.zhyshko.dto.User getUserByUsername(String username){
		return mapper.map(userRepository.findByUsername(username).orElse(null), com.zhyshko.dto.User.class);
	}
	
	
	@Transactional
	public void deleteUser(UUID id){
		userRepository.deleteById(id);
	}
	
	
	@Transactional
	public com.zhyshko.dto.User updateUser(com.zhyshko.dto.User user){
		return  mapper.map(userRepository.save(mapper.map(user, User.class)), com.zhyshko.dto.User.class);
	}
	
	
}
