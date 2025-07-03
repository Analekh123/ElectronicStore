package com.analekh.electronic.store.ElectronicStroreeeee.services;

import java.util.List;

import com.analekh.electronic.store.ElectronicStroreeeee.dto.PageableResponse;
import com.analekh.electronic.store.ElectronicStroreeeee.dto.UserDto;

public interface UserService {

	
	//create
	UserDto createUser(UserDto userDto);
	
	//update
	UserDto updateUser(UserDto userDto,String userId);
	
	
	//delete
	void deleteUser(String userId);
	
	//get all users
//	List<UserDto> getAllUser();
	
	//get single user by id
	UserDto getUser(String userId);
	
	//get user by email
	UserDto getUserByEmail(String email);
	
	//search user
	List<UserDto> searchUser(String keyword);

	PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize,String sortBy,String sortDir);
	
	//other user specific service
}
