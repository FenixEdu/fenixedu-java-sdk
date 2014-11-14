package org.fenixedu.sdk.exception;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class FenixEduClientException extends RuntimeException {

    private static final long serialVersionUID = -889474687022606439L;

    private static final String ERROR_KEY = "error";
    private static final String ERROR_DESCRIPTION_KEY = "error_description";

    private String error = null;
    private String errorDescription = null;

    public FenixEduClientException(String message, Throwable cause) {
        super(message, cause);
        //this.error = message;
        //this.errorDescription = message;
        //decode(message);
    }

    public FenixEduClientException(String message) {
        this(message, null);
    }

    private void decode(String str) {
        if (str != null && str.startsWith("{")) {
            try {
                JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
                if (jsonObject.has(ERROR_KEY)) {
                    this.error = jsonObject.get(ERROR_KEY).getAsString();
                    this.errorDescription = jsonObject.get(ERROR_DESCRIPTION_KEY).getAsString();
                }
            } catch (JsonParseException ignore) {

            }
        }
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
