package org.fenixedu.sdk;

import org.fenixedu.sdk.api.DotEndpoint;

public class RequestFactory {

    public static HttpRequest fromDotEndpoint(ApplicationConfiguration config, DotEndpoint endpoint,
            String... endpointArgs) {
        return new HttpRequest(endpoint.generateEndpoint(config, endpointArgs), endpoint.getHttpMethod()).accepts(endpoint
                .getMediaType());
    }

}
