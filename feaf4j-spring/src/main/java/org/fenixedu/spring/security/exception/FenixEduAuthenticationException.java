package org.fenixedu.spring.security.exception;

public class FenixEduAuthenticationException extends RuntimeException {

    private static final long serialVersionUID = -1166551971829078885L;

    private final String error;

    private final String errorDescription;

    public FenixEduAuthenticationException(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
