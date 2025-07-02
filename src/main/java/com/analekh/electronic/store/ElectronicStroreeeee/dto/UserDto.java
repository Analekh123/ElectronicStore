package com.analekh.electronic.store.ElectronicStroreeeee.dto;

import com.analekh.electronic.store.ElectronicStroreeeee.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

	private String userId;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String gender;
	
	private String about;
}
