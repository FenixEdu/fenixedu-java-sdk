package org.fenixedu.sdk.exception;

public class AccessTokenExpiredException extends ApiClientException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AccessTokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessTokenExpiredException(String message) {
        super(message);
    }

}
