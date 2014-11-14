package org.fenixedu.sdk.exception;

public class AccessTokenInvalidFormatException extends FenixEduClientException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AccessTokenInvalidFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessTokenInvalidFormatException(String message) {
        super(message);
    }

}
