package org.fenixedu.sdk.exception;

import org.fenixedu.sdk.Errors;

public class ExceptionFactory {
    public static ApiClientException createException(String error, String errorDescription) {
        if (error.equals(Errors.INVALID_SCOPE)) {
            return new InvalidScopeException(errorDescription);
        } else if (error.equals(Errors.ACCESS_TOKEN_INVALID)) {
            return new AccessTokenInvalidException(errorDescription);
        } else if (error.equals(Errors.ACCESS_TOKEN_EXPIRED)) {
            return new AccessTokenExpiredException(errorDescription);
        } else if (error.equals(Errors.ACCESS_TOKEN_INVALID_FORMAT)) {
            return new AccessTokenInvalidFormatException(errorDescription);
        } else if (error.equals(Errors.INVALID_GRANT)) {
            return new InvalidGrantException(errorDescription);
        } else if (error.equals(Errors.REFRESH_TOKEN_INVALID)) {
            return new RefreshTokenInvalidException(errorDescription);
        } else if (error.equals(Errors.ACCESS_TOKEN_INVALID_FORMAT)) {
            return new RefreshTokenInvalidFormatException(errorDescription);
        } else {
            return new ApiClientException(errorDescription);
        }
    }
}
