package org.fenixedu.sdk.api;

import org.fenixedu.sdk.ApplicationConfiguration;
import org.fenixedu.sdk.DotScope;
import org.fenixedu.sdk.HttpMethod;
import org.fenixedu.sdk.MediaType;

import com.google.gson.JsonObject;

public enum DotEndpoint {
    OAUTH_USER_DIALOG("/oauth/userdialog", DotScope.AUTH, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class),
    OAUTH_ACCESS_TOKEN("/oauth/access_token", DotScope.AUTH, HttpMethod.POST, MediaType.APPLICATION_JSON, JsonObject.class),
    OAUTH_REFRESH_ACCESS_TOKEN("/oauth/refresh_token", DotScope.AUTH, HttpMethod.POST, MediaType.APPLICATION_JSON,
            JsonObject.class),

    EXPENDITURES_SEARCH_MISSIONS("mission/v1/search", DotScope.EXPENDITURES, HttpMethod.GET, MediaType.APPLICATION_JSON,
            JsonObject.class),
    EXPENDITURES_MISSION("mission/v1", DotScope.EXPENDITURES, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class);

    private String pathRegex;
    private DotScope scope;
    private HttpMethod httpMethod;
    private MediaType mediaType;
    private Class<?> responseClass;

    private final static String ENDPOINT_PREFIX = "/api/";

    private DotEndpoint(String path, DotScope scope, HttpMethod httpMethod, MediaType mediaType, Class<?> responseClass) {
        this.pathRegex = path;
        this.scope = scope;
        this.httpMethod = httpMethod;
        this.mediaType = mediaType;
        this.responseClass = responseClass;
    }

    public String generateEndpoint(ApplicationConfiguration config, String... args) {
        final Object[] endpointArgs = args;
        return config.getBaseUrl() + (this.scope == DotScope.AUTH ? "" : ENDPOINT_PREFIX)
                + String.format(pathRegex, endpointArgs);
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public DotScope getScope() {
        return scope;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public Class<?> getResponseClass() {
        return responseClass;
    }
}
