package controller;

public class EntityNotFoundException extends Exception {
	EntityNotFoundException(String error) {
		super(error);
	}
}
