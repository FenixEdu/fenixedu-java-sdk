package org.fenixedu.sdk;

public class FenixEduUserDetails {

    private String username;
    private final Authorization authorization;

    public FenixEduUserDetails(Authorization authorization) {
        this.authorization = authorization;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

}
