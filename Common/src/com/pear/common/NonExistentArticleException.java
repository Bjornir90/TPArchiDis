package com.pear.common;

public class NonExistentArticleException extends Exception {
	public NonExistentArticleException() {
	}

	public NonExistentArticleException(String message) {
		super(message);
	}
}
