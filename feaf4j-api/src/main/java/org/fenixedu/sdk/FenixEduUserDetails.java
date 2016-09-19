package org.fenixedu.sdk;

public class FenixEduUserDetails {

    private String username;
    private final OAuthAuthorization authorization;

    public FenixEduUserDetails(OAuthAuthorization authorization) {
        this.authorization = authorization;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public OAuthAuthorization getAuthorization() {
        return authorization;
    }

}
