package pt.ist.fenixedu.sdk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public final class FenixEduClient {

    private static final Logger LOG = LoggerFactory.getLogger(FenixEduClient.class);

    private final FenixEduConfig config;

    private final Client client;

    FenixEduClient() throws IOException {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("/fenixedu.config"));
        String consumerKey = props.getProperty(FenixEduConfig.CONSUMER_KEY);
        String consumerSecret = props.getProperty(FenixEduConfig.CONSUMER_SECRET);
        String callbackUrl = props.getProperty(FenixEduConfig.CALLBACK_URL, null);
        String baseUrl = props.getProperty(FenixEduConfig.BASE_URL);

        String accessToken = props.getProperty(FenixEduConfig.ACCESS_TOKEN, null);

        this.client = Client.create();
        this.config = new FenixEduConfig(consumerKey, consumerSecret, accessToken, baseUrl, callbackUrl);

    }

    public String getAuthenticationUrl() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("client_id", this.config.getConsumerKey());
        paramMap.put("redirect_uri", this.config.getCallbackUrl());

        String url = String.format("%s/oauth/userdialog", config.getBaseUrl());
        String[] params = new String[] { "client_id", "redirect_uri" };

        List<String> queryParams = new ArrayList<String>();

        for (String param : params) {
            queryParams.add(Joiner.on("=").join(param, paramMap.get(param)));
        }

        WebResource resource = client.resource(url + "?" + Joiner.on("&").join(queryParams));
        String authenticationUrl = resource.getURI().toString();
        LOG.debug("Authentication URL provided is: {}", authenticationUrl);

        return authenticationUrl;
    }

    public void setCode(String code) {
        LOG.debug("Setting Code: {}", code);
        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", this.config.getConsumerKey());
        params.put("redirect_uri", this.config.getCallbackUrl());
        params.put("client_secret", this.config.getConsumerSecret());
        params.put("code", code);
        params.put("grant_type", "authorization_code");

        String token_url = String.format("%s/oauth/access_token", this.config.getBaseUrl());

        List<String> args = new ArrayList<String>();

        String[] headers = new String[] { "client_id", "client_secret", "redirect_uri", "code", "grant_type" };

        for (String key : headers) {
            args.add(Joiner.on("=").join(key, params.get(key)));
        }
        String url_encoded = Joiner.on("&").join(args);
        WebResource resource = client.resource(token_url);
        String rsp = resource.entity(url_encoded, MediaType.APPLICATION_FORM_URLENCODED).post(String.class);
        JsonObject response = new JsonParser().parse(rsp).getAsJsonObject();
        String accessToken = response.get("access_token").getAsString();
        String refreshToken = response.get("refresh_token").getAsString();
        config.setAccessToken(accessToken);
        config.setRefreshToken(refreshToken);
    }

    private <T extends JsonElement> T invoke(String endpoint, String httpMethod, Class<T> clazz) {
        return invoke(endpoint, httpMethod, clazz, null);

    }

    @SuppressWarnings("unchecked")
    private <T extends JsonElement> T invoke(String endpoint, String httpMethod, Class<T> clazz, Map<String, String> queryParams) {
        WebResource webResource = client.resource(config.getBaseUrl() + endpoint);
        if (queryParams != null) {
            for (String key : queryParams.keySet()) {
                webResource = webResource.queryParam(key, queryParams.get(key));
            }
        }
        webResource = webResource.queryParam("access_token", config.getAccessToken());
        System.out.println(webResource.getURI());
        webResource.accept(MediaType.APPLICATION_JSON);
        String rsp = webResource.method(httpMethod, String.class);
        JsonElement result = new JsonParser().parse(rsp);
        if (clazz.equals(JsonArray.class)) {
            return (T) result.getAsJsonArray();
        } else {
            return (T) result.getAsJsonObject();
        }
    }

    private static final String PUBLIC_BASE = "/api/fenix/v1/";

    private static final String PRIVATE_BASE = PUBLIC_BASE;

    private static final String privateEndpoint(String path) {
        return PRIVATE_BASE + path;
    }

    private static final String publicEndpoint(String path) {
        return PUBLIC_BASE + path;
    }

    /**
     * Obtains the university news and events RSS feed urls.
     * 
     * <p>
     * <b>Scope:</b> Public
     * </p>
     * 
     * 
     * @return the urls for the university news and events RSS
     */
    public JsonObject getAbout() {
        return invoke(publicEndpoint("about"), HttpMethod.GET, JsonObject.class);
    }

    /**
     * Obtains information about the person in context.
     * 
     * </p>
     * <b>Scope:</b> Info
     * </p>
     * 
     * @return information about the person
     */
    public JsonObject getPerson() {
        return invoke(privateEndpoint("person"), HttpMethod.GET, JsonObject.class);
    }

    /**
     * Obtains information about the curriculum of the person in session.
     * 
     * @return information about the person curriculum
     */
    public JsonArray getPersonCurriculum() {
        return invoke(privateEndpoint("person/curriculum"), HttpMethod.GET, JsonArray.class);
    }

    /**
     * Obtains a calendar with lessons of either the student or the faculty in context.
     * 
     * <p>
     * <b>Scope:</b> Schedule
     * </p>
     * 
     * @param format
     *            the response format, which can either be iCal or JSON.
     * @return the calendar with classes in the format requested.
     */
    public JsonObject getPersonCalendarClasses(CalendarFormat format) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("format", format.toString());
        return invoke(privateEndpoint("person/calendar/classes"), HttpMethod.GET, JsonObject.class, params);
    }

    /**
     * Obtains the calendar of all written evaluations (tests and exams) for
     * either a student or a faculty.
     * 
     * <p>
     * <b>Scope:</b> Schedule
     * </p>
     * 
     * @param format
     *            the response format, which can either be iCal or JSON.
     * @return the calendar with classes in the format requested.
     */
    public JsonObject getPersonCalendarEvaluations(CalendarFormat format) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("format", format.toString());
        return invoke(privateEndpoint("person/calendar/evaluations"), HttpMethod.GET, JsonObject.class, params);
    }

    public JsonArray getPersonEvaluations() {
        return invoke(privateEndpoint("person/evaluations"), HttpMethod.GET, JsonArray.class);
    }

    /**
     * Enrolls or withdraws the student from a given evaluation.
     * 
     * <p>
     * <b>Scope</b>: Enrollments
     * </p>
     * 
     * @param evaluationId
     *            the id of the evaluation to enroll in or withdraw from.
     * @param action
     *            the enroll or withdraw action.
     * @return the array of evaluations of the student
     */
    public JsonArray enrollPersonInEvaluation(String evaluationId, EnrolAction action) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("enrol", action.toString());
        return invoke(privateEndpoint("person/evaluations/" + evaluationId), HttpMethod.PUT, JsonArray.class, params);
    }

    /**
     * Obtains the payments for the current person in context.
     * 
     * <p>
     * <b>Scope:</b> Personal
     * </p>
     * 
     * @return the payments of the person.
     */
    public JsonObject getPersonPayments() {
        return invoke(privateEndpoint("person/payments"), HttpMethod.GET, JsonObject.class);
    }

    /**
     * Obtains information about the course identified with the given id.
     * 
     * @param courseId the id of the course to obtain information about
     * @return information about the course with the given id.
     */
    public JsonObject getCourse(String courseId) {
        return invoke(publicEndpoint("courses/" + courseId), HttpMethod.GET, JsonObject.class);
    }

    /**
     * Obtains the course evaluations for the course identified with the given id
     * 
     * @param courseId the id of the course to retrieve the course evaluations
     * @return information about the courses evaluations of the given id
     */
    public JsonObject getCourseEvaluations(String courseId) {
        return invoke(publicEndpoint("courses/" + courseId + "/evaluations"), HttpMethod.GET, JsonObject.class);
    }

    /**
     * Obtains information about the course groups for the course with the given id.
     * 
     * @param courseId the id of the course to retrieve the groups information from
     * @return information about course groups
     */
    public JsonObject getCourseGroups(String courseId) {
        return invoke(publicEndpoint("courses/" + courseId + "/groups"), HttpMethod.GET, JsonObject.class);
    }

    /**
     * Obtains schedule information about the course identified by the given id.
     * 
     * <p>
     * <b>Scope:</b> Courses
     * </p>
     * 
     * @param courseId the id of the course to obtain the schedule information from
     * @return information about the course schedule
     */
    public JsonObject getCourseSchedule(String courseId) {
        return invoke(publicEndpoint("courses/" + courseId + "/schedule"), HttpMethod.GET, JsonObject.class);
    }

    /**
     * Obtains the student list for the course identified by the given id.
     * 
     * @param courseId the id of the course to obtain the student list
     * @return the list of course students
     */
    public JsonObject getCourseStudents(String courseId) {
        return invoke(publicEndpoint("courses/" + courseId + "/students"), HttpMethod.GET, JsonObject.class);
    }

    /**
     * Obtains an array containing both campus spaces.
     * 
     * @return the array of both campus spaces.
     */
    public JsonArray getSpaces() {
        return invoke(publicEndpoint("spaces"), HttpMethod.GET, JsonArray.class);
    }

    /**
     * Obtains space information regarding space type (Campus, Building, Floor
     * or Room)
     * 
     * @param spaceId
     *            the id of the space
     * @param day
     *            the day for which the events should be listed for that room
     *            (e.g. "dd/mm/yyyy")
     * 
     * @return information regarding the space at a particular day
     */
    public JsonObject getSpace(String spaceId, String day) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("day", day);
        return invoke(publicEndpoint("spaces/" + spaceId), HttpMethod.GET, JsonObject.class, params);
    }

    /**
     * Obtains the degrees associated to a particular execution year
     * 
     * @param year
     *            the representative string of the execution year you wish to
     *            retrieve the courses from (e.g. 2003/2004)
     * @return a JsonArray describing the courses
     */
    public JsonArray getDegrees(String year) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("year", year);
        return invoke(publicEndpoint("degrees"), HttpMethod.GET, JsonArray.class, params);
    }

    /**
     * Retrieves information about the degree with the given id, in a particular execution
     * year.
     * 
     * <p>
     * <b>Scope:</b> Public
     * </p>
     * 
     * @param degreeId
     *            the degree id
     * @param year
     *            the execution year to retrieve the information of the degree
     * @return
     */
    public JsonObject getDegree(String degreeId, String year) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("year", year);
        return invoke(publicEndpoint("degrees/" + degreeId), HttpMethod.GET, JsonObject.class, params);
    }

    /**
     * Retrieves information about the degree courses, in a particular execution year.
     * 
     * <p>
     * <b>Scope:</b> Public
     * </p>
     * 
     * @param degreeId the id of the degree
     * @param year the execution year (e.g. 2010/2011)
     * @return
     */
    public JsonObject getDegreeCourses(String degreeId, String year) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("year", year);
        return invoke(publicEndpoint("degrees/" + degreeId), HttpMethod.GET, JsonObject.class);
    }

    /**
     * Obtains the courses of the user in context for a particular execution
     * year and semester.
     * 
     * <p>
     * <b>Scope:</b> Enrollments
     * </p>
     * 
     * @param semester
     *            either the 1st or 2nd semester (e.g. 1)
     * @param year
     *            the execution year (e.g. 2003/2004)
     * @return both the courses that the person is teaching and/or enrolled on.
     */
    public JsonObject getPersonCourses(int semester, String year) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("sem", String.valueOf(semester));
        params.put("year", year);
        return invoke(privateEndpoint("person/courses"), HttpMethod.GET, JsonObject.class, params);
    }

    public FenixEduConfig getConfig() {
        return config;
    }
}
