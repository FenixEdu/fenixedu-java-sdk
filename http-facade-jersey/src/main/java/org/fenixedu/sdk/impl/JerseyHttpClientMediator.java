package org.fenixedu.sdk.impl;

import javax.ws.rs.core.MediaType;

import org.fenixedu.sdk.ClientResponse;
import org.fenixedu.sdk.ClientResponse.Status;
import org.fenixedu.sdk.HttpClient;
import org.fenixedu.sdk.HttpRequest;
import org.fenixedu.sdk.exception.ApiClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class JerseyHttpClientMediator implements HttpClient {

    private static final Logger logger = LoggerFactory.getLogger(JerseyHttpClientMediator.class);

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
    public ClientResponse handleHttpRequest(HttpRequest httpRequest) {
        logger.debug("Deciding the HTTP Request delegate based on HTTP Method");
        String url = httpRequest.getUrl();
        logger.debug("HTTP Request URL is: {}", url);
        try {
            switch (httpRequest.getHttpMethod()) {
            case GET:
                return handleGetRequest(url, httpRequest);
            case POST:
                return handlePostRequest(url, httpRequest);
            default:
                throw new ApiClientException("Unhandled HttpMethod", null);
            }
        } catch (Exception e) {
            throw new ApiClientException("Error handling request", e);
        }
    }

    public ClientResponse handleGetRequest(String url, HttpRequest httpRequest) {
        logger.debug("HTTP Request delegated to GET handler");
        WebResource webResource = client.resource(httpRequest.getUrl());
        if (httpRequest.getQueryParams() != null) {
            for (String key : httpRequest.getQueryParams().keySet()) {
                webResource = webResource.queryParam(key, httpRequest.getQueryParams().get(key));
            }
        }
        com.sun.jersey.api.client.ClientResponse clientResponse =
                webResource.accept(httpRequest.getAcceptedMediaType().getMediaType()).method(httpRequest.getHttpMethod().name(),
                        com.sun.jersey.api.client.ClientResponse.class);
        if (clientResponse.getStatus() == Status.OK.getStatusCode()) {
            String rsp = clientResponse.getEntity(String.class);
            org.fenixedu.sdk.ClientResponse.Status s = Status.fromStatusCode(clientResponse.getStatus());
            return new ClientResponse(s, rsp);
        } else {
            throw new ApiClientException(clientResponse.getEntity(String.class), null);
        }
    }

    public ClientResponse handlePostRequest(String url, HttpRequest httpRequest) {
        logger.debug("HTTP Request delegated to POST handler");
        WebResource webResource = client.resource(httpRequest.getUrl());
        if (httpRequest.getQueryParams() != null) {
            for (String key : httpRequest.getQueryParams().keySet()) {
                webResource = webResource.queryParam(key, httpRequest.getQueryParams().get(key));
            }
        }
        com.sun.jersey.api.client.ClientResponse clientResponse =
                webResource.accept(httpRequest.getAcceptedMediaType().getMediaType()).type(MediaType.APPLICATION_FORM_URLENCODED)
                        .post(com.sun.jersey.api.client.ClientResponse.class, new String(httpRequest.getBody()));
        if (clientResponse.getStatus() == Status.OK.getStatusCode()) {
            String rsp = clientResponse.getEntity(String.class);
            org.fenixedu.sdk.ClientResponse.Status s = Status.fromStatusCode(clientResponse.getStatus());
            return new ClientResponse(s, rsp);
        } else {
            throw new ApiClientException(clientResponse.getEntity(String.class), null);
        }
    }
}
