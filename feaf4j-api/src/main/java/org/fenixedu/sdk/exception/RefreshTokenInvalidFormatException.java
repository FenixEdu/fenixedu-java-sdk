package org.fenixedu.sdk.exception;

public class RefreshTokenInvalidFormatException extends FenixEduClientException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RefreshTokenInvalidFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public RefreshTokenInvalidFormatException(String message) {
        super(message);
    }

}
