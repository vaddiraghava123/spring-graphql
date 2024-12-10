package com.vaddi.spring_graphql.entity;

public class ExceptionHandler {

	public static RuntimeException throwResourceNotFoundException() {
		return new RuntimeException("Resource Not found ");
	}
}
