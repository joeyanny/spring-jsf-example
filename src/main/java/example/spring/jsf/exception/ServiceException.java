package example.spring.jsf.exception;

/**
 * Provides a layer of abstraction for exceptions
 * handled by the service layer.
 */
public class ServiceException extends Exception {

	/**
	 * Wraps an exception from the integration layer
	 * 
	 * @param message User friendly message explaining the error
	 * @param e Exception from the integration layer
	 */
	public ServiceException(String message, Exception e) {
		super(message, e);
	}
}
