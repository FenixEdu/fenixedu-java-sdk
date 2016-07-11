package org.fenixedu.sdk.api;

import org.fenixedu.sdk.ApplicationConfiguration;
import org.fenixedu.sdk.HttpMethod;
import org.fenixedu.sdk.MediaType;
import org.fenixedu.sdk.Scope;

import com.google.gson.JsonObject;

public enum DotEndpoint {
    OAUTH_USER_DIALOG("/oauth/userdialog", Scope.AUTH, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class),
    OAUTH_ACCESS_TOKEN("/oauth/access_token", Scope.AUTH, HttpMethod.POST, MediaType.APPLICATION_JSON, JsonObject.class),
    OAUTH_REFRESH_ACCESS_TOKEN("/oauth/refresh_token", Scope.AUTH, HttpMethod.POST, MediaType.APPLICATION_JSON, JsonObject.class),

    EXPENDITURES_SEARCH_MISSIONS("mission/search", Scope.EXPENDITURES, HttpMethod.GET, MediaType.APPLICATION_JSON,
            JsonObject.class),
    EXPENDITURES_MISSION("mission", Scope.EXPENDITURES, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class);

    private String pathRegex;
    private Scope scope;
    private HttpMethod httpMethod;
    private MediaType mediaType;
    private Class<?> responseClass;

    private final static String ENDPOINT_PREFIX = "/api/";

    private DotEndpoint(String path, Scope scope, HttpMethod httpMethod, MediaType mediaType, Class<?> responseClass) {
        this.pathRegex = path;
        this.scope = scope;
        this.httpMethod = httpMethod;
        this.mediaType = mediaType;
        this.responseClass = responseClass;
    }

    public String generateEndpoint(ApplicationConfiguration config, String... args) {
        final Object[] endpointArgs = args;
        return config.getBaseUrl() + (this.scope == Scope.AUTH ? "" : ENDPOINT_PREFIX) + String.format(pathRegex, endpointArgs);
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public Scope getScope() {
        return scope;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public Class<?> getResponseClass() {
        return responseClass;
    }
}
