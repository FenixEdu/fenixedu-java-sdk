package org.fenixedu.sdk.api;

import java.io.File;

import org.fenixedu.sdk.ApplicationConfiguration;
import org.fenixedu.sdk.HttpMethod;
import org.fenixedu.sdk.MediaType;
import org.fenixedu.sdk.FenixEduScope;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public enum FenixEduEndpoint {
    OAUTH_USER_DIALOG("/oauth/userdialog", FenixEduScope.AUTH, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class),
    OAUTH_ACCESS_TOKEN("/oauth/access_token", FenixEduScope.AUTH, HttpMethod.POST, MediaType.APPLICATION_JSON, JsonObject.class),
    OAUTH_REFRESH_ACCESS_TOKEN("/oauth/refresh_token", FenixEduScope.AUTH, HttpMethod.POST, MediaType.APPLICATION_JSON, JsonObject.class),

    ABOUT("about", FenixEduScope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), ASSIDUITY(
            "employeeAssiduity/employee", FenixEduScope.ASSIDUITY, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class),
    DOMAIN_MODEL("domainModel", FenixEduScope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), ACADEMIC_TERMS(
            "academicterms", FenixEduScope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), CANTEEN("canteen",
            FenixEduScope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class), CONTACTS("contacts", FenixEduScope.PUBLIC,
            HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class),

    COURSE("courses/%s", FenixEduScope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), COURSE_EVALUATIONS(
            "courses/%s/evaluations", FenixEduScope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class), COURSE_GROUPS(
            "courses/%s/groups", FenixEduScope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class), COURSE_SCHEDULE(
            "courses/%s/schedule", FenixEduScope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), COURSE_STUDENTS(
            "courses/%s/students", FenixEduScope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class),

    DEGREES("degrees", FenixEduScope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class), DEGREE("degrees/%s",
            FenixEduScope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), DEGREE_COURSES("degrees/%s/courses",
            FenixEduScope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class),

    PERSON("person", FenixEduScope.PERSONAL, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), PERSON_CALENDAR_CLASSES(
            "person/calendar/classes", FenixEduScope.SCHEDULE, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class),
    PERSON_CALENDAR_EVALUATIONS("person/calendar/evaluations", FenixEduScope.SCHEDULE, HttpMethod.GET, MediaType.APPLICATION_JSON,
            JsonObject.class), PERSON_COURSES("person/courses", FenixEduScope.CURRICULAR, HttpMethod.GET, MediaType.APPLICATION_JSON,
            JsonObject.class), PERSON_CURRICULUM("person/curriculum", FenixEduScope.CURRICULAR, HttpMethod.GET,
            MediaType.APPLICATION_JSON, JsonArray.class), PERSON_EVALUATIONS("person/evaluations", FenixEduScope.EVALUATION,
            HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class), PERSON_EVALUATION("person/evaluations/%s",
            FenixEduScope.EVALUATION, HttpMethod.PUT, MediaType.APPLICATION_JSON, JsonArray.class), PERSON_PAYMENTS("person/payments",
            FenixEduScope.PAYMENT, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class),

    SHUTTLE("shuttle", FenixEduScope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), PARKING("parking",
            FenixEduScope.PUBLIC, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonObject.class), SPACES("spaces", FenixEduScope.PUBLIC,
            HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class), SPACE("spaces/%s", FenixEduScope.PUBLIC, HttpMethod.GET,
            MediaType.APPLICATION_JSON, JsonObject.class), SPACE_BLUEPRINT("spaces/%s/blueprint", FenixEduScope.PUBLIC, HttpMethod.GET,
            MediaType.APPLICATION_JSON, File.class),

    PHD_THESIS_PROCESSES("phdThesisProcesses", FenixEduScope.DEGREE_CURRICULAR_MANAGEMENT, HttpMethod.GET, MediaType.APPLICATION_JSON, JsonArray.class);

    private String pathRegex;
    private FenixEduScope scope;
    private HttpMethod httpMethod;
    private MediaType mediaType;
    private Class<?> responseClass;

    private final static String ENDPOINT_PREFIX = "/api/fenix/v1/";

    private FenixEduEndpoint(String path, FenixEduScope scope, HttpMethod httpMethod, MediaType mediaType, Class<?> responseClass) {
        this.pathRegex = path;
        this.scope = scope;
        this.httpMethod = httpMethod;
        this.mediaType = mediaType;
        this.responseClass = responseClass;
    }

    public String generateEndpoint(ApplicationConfiguration config, String... args) {
        Object[] endpointArgs = args;
        return config.getBaseUrl() + (this.scope == FenixEduScope.AUTH ? "" : ENDPOINT_PREFIX) + String.format(pathRegex, endpointArgs);
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public FenixEduScope getScope() {
        return scope;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public Class<?> getResponseClass() {
        return responseClass;
    }
}
