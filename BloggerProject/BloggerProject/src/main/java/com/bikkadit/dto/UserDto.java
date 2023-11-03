package com.bikkadit.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Integer userId;

	@Email(message = "Enter valid Email")
	private String emailId;

	@NotEmpty
	@Size(min = 4, message = "User name must greeter than 4 charecter ")
	private String userName;

	@NotEmpty(message = "Enter Valid Password")
	private String password;

	@NotEmpty
	private String about;

}
