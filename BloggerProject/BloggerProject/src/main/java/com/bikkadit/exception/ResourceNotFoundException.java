package com.bikkadit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ResourceNotFoundException extends RuntimeException {

	String recourceName;

	String filedName;

	long filedValue;

	public ResourceNotFoundException(String recourceName, String filedName, long username) {
		super(String.format("%s resource not found %s : %s", recourceName, filedName, username));
		this.recourceName = recourceName;
		this.filedName = filedName;
		this.filedValue = username;
	}

}
