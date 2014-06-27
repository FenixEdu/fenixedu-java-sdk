package org.fenixedu.sdk.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.fenixedu.sdk.ClientResponse;
import org.fenixedu.sdk.ClientResponse.Status;
import org.fenixedu.sdk.HttpClient;
import org.fenixedu.sdk.HttpRequest;
import org.fenixedu.sdk.exception.FenixEduClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.squareup.okhttp.OkHttpClient;

public class OkHttpClientMediator implements HttpClient {

    private static final Logger logger = LoggerFactory.getLogger(OkHttpClientMediator.class);

    private final OkHttpClient client;

    private final static OkHttpClientMediator SINGLETON = new OkHttpClientMediator();

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
        } catch (Throwable e) {
            throw new FenixEduClientException("Error handling request", e);
        }
    }

    private ClientResponse handleGetRequest(String url, HttpRequest httpRequest) {
        logger.debug("HTTP Request delegated to GET handler");
        InputStream in = null;
        try {
            HttpURLConnection connection = client.open(new URL(url));
            in = connection.getInputStream();
            return new ClientResponse(Status.fromStatusCode(connection.getResponseCode()), readStringFromInputStream(in));
        } catch (IOException e) {
            throw new FenixEduClientException("Problem in handling GET request", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new FenixEduClientException("Problem in closing input stream opened during GET request", e);
                }
            }
        }
    }

    private String readStringFromInputStream(InputStream inputStream) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    private ClientResponse handlePostRequest(String url, HttpRequest postRequest) throws Exception {
        logger.debug("HTTP Request delegated to POST handler");
        HttpURLConnection connection = client.open(new URL(url));
        if (postRequest.getQueryParams() != null) {
            for (String key : postRequest.getQueryParams().keySet()) {
                connection.addRequestProperty(key, postRequest.getQueryParams().get(key));
            }
        }
        OutputStream out = null;
        InputStream in = null;
        try {
            connection.setRequestMethod("POST");
            if (postRequest.getBody() != null) {
                out = connection.getOutputStream();
                out.write(postRequest.getBody());
                out.close();
            }
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                String errorMessage = readStringFromInputStream(connection.getErrorStream());
                logger.debug("FenixEdu responded: {}", errorMessage);
                throw new FenixEduClientException(errorMessage, null);
            }
            in = connection.getInputStream();
            return new ClientResponse(Status.fromStatusCode(connection.getResponseCode()), readStringFromInputStream(in));
        } catch (FenixEduClientException e) {
            logger.error("Could not handle POST request", url, e);
            throw e;
        } finally {
            // Clean up.
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }
}
