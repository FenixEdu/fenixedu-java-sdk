package org.fenixedu.sdk.impl;

import org.fenixedu.sdk.ClientResponse;
import org.fenixedu.sdk.ClientResponse.Status;
import org.fenixedu.sdk.HttpClient;
import org.fenixedu.sdk.HttpRequest;
import org.fenixedu.sdk.exception.FenixEduClientException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class JerseyHttpClientMediator implements HttpClient {

    private final Client client;

    private final static JerseyHttpClientMediator SINGLETON = new JerseyHttpClientMediator();

    private JerseyHttpClientMediator() {
        this.client = Client.create();
    }

    public Client getClient() {
        return client;
    }

    public static HttpClient getInstance() {
        return SINGLETON;
    }

    @Override
    public ClientResponse handleHttpRequest(HttpRequest httpRequest) throws FenixEduClientException {
        WebResource webResource = client.resource(httpRequest.getUrl());
        if (httpRequest.getQueryParams() != null) {
            for (String key : httpRequest.getQueryParams().keySet()) {
                webResource = webResource.queryParam(key, httpRequest.getQueryParams().get(key));
            }
        }
        webResource.accept(httpRequest.getAcceptedMediaType().getMediaType());
        com.sun.jersey.api.client.ClientResponse clientResponse =
                webResource.method(httpRequest.getHttpMethod().name(), com.sun.jersey.api.client.ClientResponse.class);
        if (clientResponse.getStatus() == Status.OK.getStatusCode()) {
            String rsp = clientResponse.getEntity(String.class);
            org.fenixedu.sdk.ClientResponse.Status s = Status.fromStatusCode(clientResponse.getStatus());
            return new ClientResponse(s, rsp);
        } else {
            throw new FenixEduClientException(clientResponse.getEntity(String.class), null);
        }
    }
}
