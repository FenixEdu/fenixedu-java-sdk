package pt.ist.fenixedu.sdk;

public class FEException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;

    private final Throwable throwable;

    public FEException(String message, Throwable throwable) {
        this.message = message;
        this.throwable = throwable;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Throwable getError() {
        return throwable;
    }

}
