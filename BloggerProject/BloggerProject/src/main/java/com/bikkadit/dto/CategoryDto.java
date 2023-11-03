package com.bikkadit.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	private Integer categoryID;

	@NotEmpty
	@Size(min = 100, max = 200, message = "Title have min 100 and max 200 charecter")
	private String categoryTitle;

	@NotEmpty
	@Size(min = 100, max = 1000)
	private String categoryDesc;

}
