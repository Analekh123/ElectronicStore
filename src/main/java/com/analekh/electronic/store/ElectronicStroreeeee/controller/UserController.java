package com.analekh.electronic.store.ElectronicStroreeeee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.analekh.electronic.store.ElectronicStroreeeee.dto.ApiResponseMessage;
import com.analekh.electronic.store.ElectronicStroreeeee.dto.PageableResponse;
import com.analekh.electronic.store.ElectronicStroreeeee.dto.UserDto;
import com.analekh.electronic.store.ElectronicStroreeeee.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//create
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto user = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(user,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("userId") String userId,@RequestBody UserDto userDto){
		UserDto updatedUserDto = userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(updatedUserDto,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId){
		userService.deleteUser(userId);
		
		ApiResponseMessage message = ApiResponseMessage.builder()
										.message("user is deleted Successfully !!")
										.success(true)
										.status(HttpStatus.OK)
										.build();
		
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	//get all
//	@GetMapping
//	public ResponseEntity<List<UserDto>> getAllUsers(){
//		return new ResponseEntity<List<UserDto>>(userService.getAllUser(),HttpStatus.OK);
//	}
	
	//get all
	@GetMapping
	public ResponseEntity<PageableResponse<UserDto>> getAllUsers(
			@RequestParam(value="pageNumber",defaultValue = "0",required = false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue = "10",required = false) int pageSize,
			@RequestParam(value="sortBy",defaultValue = "name",required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = "ASC",required = false) String sortDir
			){
		return new ResponseEntity<>(userService.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
	
	}
	
	//get single
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUse(@PathVariable String userId){
		return new ResponseEntity<UserDto>(userService.getUser(userId),HttpStatus.OK);
	}
	
	//get by email
	@GetMapping("/email/{email}")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
		return new ResponseEntity<UserDto>(userService.getUserByEmail(email),HttpStatus.OK);
	}
	
	//search user
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords){
		return new ResponseEntity<>(userService.searchUser(keywords),HttpStatus.OK);
	}

}
