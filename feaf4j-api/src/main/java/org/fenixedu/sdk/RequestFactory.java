package org.fenixedu.sdk;

import org.fenixedu.sdk.api.FenixEduEndpoint;

public class RequestFactory {

    public static HttpRequest fromFenixEduEndpoint(ApplicationConfiguration config, FenixEduEndpoint endpoint,
            String... endpointArgs) {
        return new HttpRequest(endpoint.generateEndpoint(config, endpointArgs), endpoint.getHttpMethod()).accepts(endpoint
                .getMediaType());
    }

}
