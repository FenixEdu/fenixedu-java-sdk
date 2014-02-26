package org.fenixedu.sdk;


public interface Authorization {

    public HttpRequest authorize(HttpRequest webResource);

    public OAuthAuthorization asOAuthAuthorization();

}
