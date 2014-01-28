package pt.ist.fenixedu.sdk.auth;

import java.util.Map;

import com.sun.jersey.api.client.WebResource;

public interface Authorization {

    public WebResource authorize(WebResource webResource);

    public void setCredentials(Map<String, String> credentialMap);

}
