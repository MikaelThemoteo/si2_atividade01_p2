package com.livraria.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Long id) {
		super("Not found ID "+id);
	}

}
