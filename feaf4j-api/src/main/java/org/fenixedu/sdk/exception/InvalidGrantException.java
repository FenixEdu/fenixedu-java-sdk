package org.fenixedu.sdk.exception;

public class InvalidGrantException extends FenixEduClientException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InvalidGrantException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidGrantException(String message) {
        super(message);
    }

}
