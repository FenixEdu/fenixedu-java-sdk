package org.fenixedu.sdk.exception;

public class AccessTokenInvalidException extends ApiClientException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AccessTokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessTokenInvalidException(String message) {
        super(message);
    }

}
