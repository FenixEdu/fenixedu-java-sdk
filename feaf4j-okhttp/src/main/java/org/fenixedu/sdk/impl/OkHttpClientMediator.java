package org.fenixedu.sdk.impl;

import java.io.IOException;

import org.fenixedu.sdk.ClientResponse;
import org.fenixedu.sdk.ClientResponse.Status;
import org.fenixedu.sdk.HttpClient;
import org.fenixedu.sdk.HttpRequest;
import org.fenixedu.sdk.exception.FenixEduClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class OkHttpClientMediator implements HttpClient {

    private static final Logger logger = LoggerFactory.getLogger(OkHttpClientMediator.class);

    private final OkHttpClient client;

    private final static OkHttpClientMediator SINGLETON = new OkHttpClientMediator();

    private final static MediaType MEDIA_TYPE = MediaType.parse("application/x-www-form-urlencoded");

    private OkHttpClientMediator() {
        logger.debug("Instantiating OkHttpClient...");
        this.client = new OkHttpClient();
    }

    public OkHttpClient getClient() {
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
                throw new FenixEduClientException("Unhandled HttpMethod", null);
            }
        } catch (FenixEduClientException e) {
            throw e;

        } catch (Throwable e) {
            throw new FenixEduClientException("Error handling request", e);
        }
    }

    private ClientResponse handleGetRequest(String url, HttpRequest httpRequest) {
        logger.debug("HTTP Request delegated to GET handler");
        try {
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            return new ClientResponse(Status.fromStatusCode(response.code()), response.body().string());
        } catch (IOException e) {
            throw new FenixEduClientException("Problem in handling GET request", e);
        }
    }

    private ClientResponse handlePostRequest(String url, HttpRequest postRequest) throws Exception {
        logger.debug("HTTP Request delegated to POST handler");
        RequestBody body = RequestBody.create(MEDIA_TYPE, postRequest.getBody());
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        return new ClientResponse(Status.fromStatusCode(response.code()), response.body().string());

    }
}
