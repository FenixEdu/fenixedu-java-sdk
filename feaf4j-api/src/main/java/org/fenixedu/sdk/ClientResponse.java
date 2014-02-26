package org.fenixedu.sdk;

public class ClientResponse {

    private final Status status;
    private final String response;

    public ClientResponse(Status status, String response) {
        this.status = status;
        this.response = response;
    }

    public int getStatusCode() {
        return status.getStatusCode();
    }

    public String getResponse() {
        return response;
    }

    public enum Status {
        OK(200, "OK"), CREATED(201, "Created"), BAD_REQUEST(400, "Bad Request");

        private final int statusCode;
        private final String description;

        private Status(int statusCode, String description) {
            this.statusCode = statusCode;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public static Status fromStatusCode(int responseCode) {
            switch (responseCode) {
            case 200:
                return OK;
            case 201:
                return CREATED;
            case 400:
                return BAD_REQUEST;
            }
            return null;
        }
    }

}
