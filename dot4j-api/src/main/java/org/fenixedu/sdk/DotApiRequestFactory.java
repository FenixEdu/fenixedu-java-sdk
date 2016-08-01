package org.fenixedu.sdk;

import org.fenixedu.sdk.api.DotEndpoint;

public class DotApiRequestFactory {

    public static HttpRequest fromDotEndpoint(ApplicationConfiguration config, DotEndpoint endpoint,
            String... endpointArgs) {
        return new HttpRequest(endpoint.generateEndpoint(config, endpointArgs), endpoint.getHttpMethod()).accepts(endpoint
                .getMediaType());
    }

}
