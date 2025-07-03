package com.analekh.electronic.store.ElectronicStroreeeee.dto;

import com.analekh.electronic.store.ElectronicStroreeeee.entities.User;
import com.analekh.electronic.store.ElectronicStroreeeee.validate.ImagenameValid;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	
	@Size(min=3,max=15,message="Invalid Name !!")
	private String name;
	
//	@Email(message="Invalid User Email !!")
	@Pattern(
		    regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
		    message = "Invalid Email Format"
		)
		@NotBlank(message = "Email field should not be blank")
		private String email;
	
	@NotBlank(message="Password is required")
	private String password;
	
	@Size(min=4,max=6,message="Invalid gender !!")
	private String gender;
	
	@NotBlank(message="Write something about yourself")
	private String about;
	
	//@pattern
	//custom validator
	
	@ImagenameValid
	private String imageName;
}
