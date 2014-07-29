package org.fenixedu.sdk.api;

import java.io.File;

import org.fenixedu.sdk.ApplicationConfiguration;
import org.fenixedu.sdk.HttpMethod;
import org.fenixedu.sdk.MediaType;
import org.fenixedu.sdk.Scope;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public enum FenixEduEndpoint {
    OAUTH_USER_DIALOG("/oauth/userdialog", Scope.AUTH, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class),
    OAUTH_ACCESS_TOKEN("/oauth/access_token", Scope.AUTH, HttpMethod.POST, MediaType.APPLICATION_JSON, JsonObject.class),
    OAUTH_REFRESH_ACCESS_TOKEN("/oauth/refresh_token", Scope.AUTH, HttpMethod.POST, MediaType.APPLICATION_JSON, JsonObject.class),

    ABOUT("about", Scope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), DOMAIN_MODEL("domainModel",
            Scope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), ACADEMIC_TERMS("academicterms",
            Scope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), CANTEEN("canteen", Scope.PUBLIC,
            HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class),

    COURSE("course", Scope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), COURSE_EVALUATIONS(
            "course/%s/evaluations", Scope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class), COURSE_GROUPS(
            "course/%s/groups", Scope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class), COURSE_SCHEDULE(
            "course/%s/schedule", Scope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), COURSE_STUDENTS(
            "course/%s/students", Scope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class),

    DEGREES("degrees", Scope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class), DEGREE("degrees/%s",
            Scope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), DEGREE_COURSES("degrees/%s/courses",
            Scope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class),

    PERSON("person", Scope.PERSONAL, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), PERSON_CALENDAR_CLASSES(
            "person/calendar/classes", Scope.SCHEDULE, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class),
    PERSON_CALENDAR_EVALUATIONS("person/calendar/evaluations", Scope.SCHEDULE, HttpMethod.GET, MediaType.APPLICATION_JSON,
            JsonObject.class), PERSON_COURSES("person/courses", Scope.CURRICULAR, HttpMethod.GET, MediaType.APPLICATION_JSON,
            JsonObject.class), PERSON_CURRICULUM("person/curriculum", Scope.CURRICULAR, HttpMethod.GET,
            MediaType.APPLICATION_JSON, JsonArray.class), PERSON_EVALUATIONS("person/evaluations", Scope.EVALUATION,
            HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class), PERSON_EVALUATION("person/evaluations/%s",
            Scope.EVALUATION, HttpMethod.PUT, MediaType.APPLICATION_JSON, JsonObject.class), PERSON_PAYMENTS("person/payments",
            Scope.PAYMENT, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class),

    SHUTTLE("shuttle", Scope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), SPACES("spaces",
            Scope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class), SPACE("spaces/%s", Scope.PUBLIC,
            HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), SPACE_BLUEPRINT("spaces/%s/blueprint", Scope.PUBLIC,
            HttpMethod.GET, MediaType.APPLICATION_JSON, File.class);

    private String pathRegex;
    private Scope scope;
    private HttpMethod httpMethod;
    private MediaType mediaType;
    private Class<?> responseClass;

    private final static String ENDPOINT_PREFIX = "/api/fenix/v1/";

    private FenixEduEndpoint(String path, Scope scope, HttpMethod httpMethod, MediaType mediaType, Class<?> responseClass) {
        this.pathRegex = path;
        this.scope = scope;
        this.httpMethod = httpMethod;
        this.mediaType = mediaType;
        this.responseClass = responseClass;
    }

    public String generateEndpoint(ApplicationConfiguration config, String... args) {
        Object[] endpointArgs = args;
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
