package emp.model;

public class empException extends RuntimeException{

	public empException() {
		super();
	}

	public empException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public empException(String message, Throwable cause) {
		super(message, cause);
	}

	public empException(String message) {
		super(message);
	}

	public empException(Throwable cause) {
		super(cause);
	}
	
}
