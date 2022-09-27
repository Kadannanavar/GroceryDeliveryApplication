package com.grocery.exception;


//@ResponseStatus(value = HttpStatus.NOT_FOUND)
//public class ResourceNotFoundException extends Exception {
//
//	private static final long serialVersionUID = 1L;
//
//	public ResourceNotFoundException(String message) {
//		super(message);
//	}

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(final String message) {
        super(message);
    }

    public ResourceNotFoundException(final Throwable cause) {
        super(cause);
    }

}