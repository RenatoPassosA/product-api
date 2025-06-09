package com.project.produtosapi.exception;

public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException(Long id)
	{
		super("Product ID:" + id + "not found");
	}
	
}