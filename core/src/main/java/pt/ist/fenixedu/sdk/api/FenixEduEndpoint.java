package pt.ist.fenixedu.sdk.api;

import java.io.File;

import javax.ws.rs.HttpMethod;

import pt.ist.fenixedu.sdk.config.FenixEduClientConfigurationManager;
import pt.ist.fenixedu.sdk.config.FenixEduClientConfigurationManager.Configuration;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public enum FenixEduEndpoint {
    ABOUT("about", HttpMethod.GET, JsonObject.class), ACADEMIC_TERMS("academicterms", HttpMethod.GET, JsonObject.class), COURSE(
            "course", HttpMethod.GET, JsonObject.class), COURSE_EVALUATIONS("course/%s/evaluations", HttpMethod.GET,
            JsonArray.class), COURSE_GROUPS("course/%s/groups", HttpMethod.GET, JsonArray.class), COURSE_SCHEDULE(
            "course/%s/schedule", HttpMethod.GET, JsonObject.class), COURSE_STUDENTS("course/%s/students", HttpMethod.GET,
            JsonObject.class), DEGREES("degrees", HttpMethod.GET, JsonArray.class), DEGREE("degrees/%s", HttpMethod.GET,
            JsonObject.class), DEGREE_COURSES("degrees/%s/courses", HttpMethod.GET, JsonArray.class), PERSON("person",
            HttpMethod.GET, JsonObject.class), PERSON_CALENDAR_CLASSES("person/calendar/classes", HttpMethod.GET,
            JsonObject.class), PERSON_CALENDAR_EVALUATIONS("person/calendar/evaluations", HttpMethod.GET, JsonObject.class),
    PERSON_COURSES("person/courses", HttpMethod.GET, JsonObject.class), PERSON_CURRICULUM("person/curriculum", HttpMethod.GET,
            JsonArray.class), PERSON_EVALUATIONS("person/evaluations", HttpMethod.GET, JsonArray.class), PERSON_EVALUATION(
            "person/evaluations/%s", HttpMethod.PUT, JsonObject.class), PERSON_PAYMENTS("person/payments", HttpMethod.GET,
            JsonObject.class), SPACES("spaces", HttpMethod.GET, JsonArray.class), SPACE("space/%s", HttpMethod.GET,
            JsonObject.class), SPACE_BLUEPRINT("spaces/%s/blueprint", HttpMethod.GET, File.class);

    private String pathRegex;
    private String httpMethod;
    private Class<?> responseClass;

    private final static String ENDPOINT_PREFIX = "/api/fenix/v1/";

    protected final Configuration config = FenixEduClientConfigurationManager.getConfiguration();

    private FenixEduEndpoint(String path, String httpMethod, Class<?> responseClass) {
        this.pathRegex = path;
        this.httpMethod = httpMethod;
        this.responseClass = responseClass;
    }

    public String generateEndpoint(Object... args) {
        return config.getBaseUrl() + ENDPOINT_PREFIX + String.format(pathRegex, args);
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public Class<?> getResponseClass() {
        return responseClass;
    }
}
