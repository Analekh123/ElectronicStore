package com.analekh.electronic.store.ElectronicStroreeeee.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analekh.electronic.store.ElectronicStroreeeee.dto.UserDto;
import com.analekh.electronic.store.ElectronicStroreeeee.entities.User;
import com.analekh.electronic.store.ElectronicStroreeeee.repositories.UserRepository;
import com.analekh.electronic.store.ElectronicStroreeeee.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		//generate unique id in string format
		String userId = UUID.randomUUID().toString();
		
		userDto.setUserId(userId);
		
		User  user = dtoToEntity(userDto);
		
		User savedUser = userRepository.save(user);
		
		UserDto newDto = entityToDto(savedUser);
		
		return newDto;
	}

	

	@Override
	public UserDto updateUser(UserDto userDto, String userId) {
		
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id"));
		user.setName(userDto.getName());
		//email update
		user.setAbout(userDto.getAbout());
		user.setGender(userDto.getGender());
		user.setPassword(userDto.getPassword());
		
		//save data
		User updatedUser =  userRepository.save(user);
		
		UserDto updatedDto = entityToDto(updatedUser);
		
		return updatedDto;
	}

	@Override
	public void deleteUser(String userId) {
		
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id"));
		
		//delete user
		userRepository.delete(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		
		List<User> users = userRepository.findAll();
		List<UserDto> dtoList = users.stream()
			.map(user -> entityToDto(user))
			.collect(Collectors.toList());
		
		return dtoList;
	}

	@Override
	public UserDto getUser(String userId) {
		
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id !!"));
		
		return entityToDto(user);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with this email"));
		
		return entityToDto(user);
	}

	@Override
	public List<UserDto> searchUser(String keyword) {
		
		List<User> users = userRepository.findByNameContaining(keyword);
		
		List<UserDto> dtoList = users.stream()
				.map(user -> entityToDto(user))
				.collect(Collectors.toList());
			
			return dtoList;
	}
	
	
	
	private UserDto entityToDto(User savedUser) {
		
//		UserDto userDto = new UserDto();
//		
//		userDto.setUserId(savedUser.getUserId());
//		userDto.setName(userDto.getName());
//		userDto.setEmail(savedUser.getEmail());
//		userDto.setPassword(savedUser.getPassword());
//		userDto.setAbout(savedUser.getAbout());
//		userDto.setGender(savedUser.getGender());
//		
//		return userDto;
		
		return mapper.map(savedUser, UserDto.class);
		
	}

	private User dtoToEntity(UserDto userDto) {
		
//		User user = User.builder()
//			.userId(userDto.getUserId())
//			.name(userDto.getName())
//			.email(userDto.getEmail())
//			.password(userDto.getPassword())
//			.about(userDto.getAbout())
//			.gender(userDto.getGender())
//			.build();
//			
//		return user;
		
		return mapper.map(userDto, User.class);
		
	}

}
