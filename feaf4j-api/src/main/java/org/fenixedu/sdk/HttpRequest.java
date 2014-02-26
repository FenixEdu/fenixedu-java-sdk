package org.fenixedu.sdk;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private final String baseUrl;
    private final HttpMethod httpMethod;

    private byte[] body;
    private Map<String, String> queryParams;
    private MediaType acceptedMediaType;

    public HttpRequest(String baseUrl, HttpMethod httpMethod) {
        this.baseUrl = baseUrl;
        this.httpMethod = httpMethod;
    }

    public HttpRequest withBody(byte[] body) {
        this.body = body;
        return this;
    }

    public HttpRequest accepts(MediaType mediaType) {
        this.acceptedMediaType = mediaType;
        return this;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public byte[] getBody() {
        return body;
    }

    public HttpRequest withBody(String body) {
        this.body = body.getBytes();
        return this;
    }

    public String getUrl() {
        if (this.queryParams != null && this.queryParams.size() > 0) {
            return baseUrl + "?" + Joiner.getEncodedQueryParams(this.queryParams);
        } else {
            return baseUrl;
        }
    }

    public HttpRequest withQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
        return this;
    }

    public HttpRequest withQueryParam(String key, String value) {
        if (this.queryParams == null) {
            this.queryParams = new HashMap<String, String>();
        }
        this.queryParams.put(key, value);
        return this;
    }

    public MediaType getAcceptedMediaType() {
        return acceptedMediaType;
    }

    public HttpRequest withAuthorization(Authorization authorization) {
        return authorization.authorize(this);
    }
}
