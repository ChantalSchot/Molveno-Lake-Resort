package Controller;

public class EntityNotFoundException extends Exception {
	EntityNotFoundException(String error) {
		super(error);
	}
}
