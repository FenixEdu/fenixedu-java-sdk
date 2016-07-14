package org.fenixedu.sdk.exception;

public class InvalidScopeException extends ApiClientException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InvalidScopeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidScopeException(String message) {
        super(message);
    }

}
