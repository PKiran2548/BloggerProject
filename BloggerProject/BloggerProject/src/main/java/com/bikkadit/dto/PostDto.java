package com.bikkadit.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	@NotEmpty
	@Size(min = 100, max = 200 , message = "Title have min 100 and max 200 char")
	private String postTitle;

	@NotEmpty
	private String containt;
	@NotEmpty
	private String imageName;
	@NotEmpty
	private Date addedDate;

	private CategoryDto category;

	private UserDto user;

}
