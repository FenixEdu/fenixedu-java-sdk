package org.fenixedu.sdk;

import org.fenixedu.sdk.exception.FenixEduClientException;

public interface HttpClient {

    public ClientResponse handleHttpRequest(HttpRequest httpRequest) throws FenixEduClientException;

}
