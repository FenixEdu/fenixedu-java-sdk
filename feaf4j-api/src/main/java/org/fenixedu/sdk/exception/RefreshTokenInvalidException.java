package org.fenixedu.sdk.exception;

public class RefreshTokenInvalidException extends FenixEduClientException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RefreshTokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public RefreshTokenInvalidException(String message) {
        super(message);
    }

}
