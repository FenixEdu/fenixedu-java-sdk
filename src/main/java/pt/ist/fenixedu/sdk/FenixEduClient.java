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

import pt.ist.fenixedu.sdk.beans.FenixCalendar;
import pt.ist.fenixedu.sdk.beans.FenixCurriculum;
import pt.ist.fenixedu.sdk.beans.FenixEvaluation;
import pt.ist.fenixedu.sdk.beans.FenixPayment;
import pt.ist.fenixedu.sdk.beans.FenixPerson;
import pt.ist.fenixedu.sdk.beans.FenixPersonCourses;
import pt.ist.fenixedu.sdk.beans.publico.FenixAbout;
import pt.ist.fenixedu.sdk.beans.publico.FenixCourse;
import pt.ist.fenixedu.sdk.beans.publico.FenixCourseEvaluation;
import pt.ist.fenixedu.sdk.beans.publico.FenixCourseGroup;
import pt.ist.fenixedu.sdk.beans.publico.FenixCourseStudents;
import pt.ist.fenixedu.sdk.beans.publico.FenixDegree;
import pt.ist.fenixedu.sdk.beans.publico.FenixExecutionCourse;
import pt.ist.fenixedu.sdk.beans.publico.FenixSchedule;
import pt.ist.fenixedu.sdk.beans.publico.FenixSpace;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * The Class FenixEduClient.
 */
public class FenixEduClient {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(FenixEduClient.class);

    /** The Constant DEFAULT_BASE_URL. */
    private static final String DEFAULT_BASE_URL = "https://fenix.ist.utl.pt";

    /** The config. */
    private final FenixEduConfig config;
    
    /** Single user config */
    private FenixEduUserConfig singleUserConfig;

    /** The client. */
    private final Client client;

    /** The gson. */
    private final Gson gson;

    /**
     * Instantiates a new fenix edu client.
     * 
     * @throws IOException Signals that an I/O exception has occurred.
     */
    FenixEduClient() throws IOException {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("/fenixedu.config"));
        String consumerKey = props.getProperty(FenixEduConfig.CONSUMER_KEY);
        String consumerSecret = props.getProperty(FenixEduConfig.CONSUMER_SECRET);
        String callbackUrl = props.getProperty(FenixEduConfig.CALLBACK_URL, null);
        String baseUrl = props.getProperty(FenixEduConfig.BASE_URL, DEFAULT_BASE_URL);

        String accessToken = props.getProperty(FenixEduConfig.ACCESS_TOKEN, null);

        this.client = Client.create();
        this.config = new FenixEduConfig(consumerKey, consumerSecret, baseUrl, callbackUrl);
        singleUserConfig = new FenixEduUserConfig();
        singleUserConfig.setAccessToken(accessToken);
        this.gson = new Gson();
    }

    /**
     * Instantiates a new fenix edu client.
     * 
     * @param config the config
     */
    public FenixEduClient(FenixEduConfig config) {
        this.client = Client.create();
        this.config = config;
        this.gson = new Gson();
        singleUserConfig = new FenixEduUserConfig();
    }

    /**
     * Gets the authentication url.
     * 
     * @return the authentication url
     */
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

    /**
     * Sets the code.
     * 
     * @param code the new code
     */
    public void setCode(String code, FenixEduUserConfig userConfig) {
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
        userConfig.setAccessToken(accessToken);
        userConfig.setRefreshToken(refreshToken);
    }

    /**
     * Invoke private.
     * 
     * @param <T> the generic type
     * @param endpoint the endpoint
     * @param httpMethod the http method
     * @param clazz the clazz
     * @return the t
     */
    private <T extends JsonElement> T invokePrivate(String endpoint, String httpMethod, Class<T> clazz, FenixEduUserConfig userConfig) {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("access_token", userConfig.getAccessToken());
        return invoke(endpoint, httpMethod, clazz, queryParams);
    }

    /**
     * Invoke private.
     * Used to invoke private endpoints. Add the access token parameter before calling the API
     * 
     * @param <T> the generic type
     * @param endpoint the endpoint
     * @param httpMethod the http method
     * @param clazz the clazz
     * @param queryParams the query params
     * @return the t
     */
    private <T extends JsonElement> T invokePrivate(String endpoint, String httpMethod, Class<T> clazz,
            Map<String, String> queryParams, FenixEduUserConfig userConfig) {
        queryParams.put("access_token", userConfig.getAccessToken());
        return invoke(endpoint, httpMethod, clazz, queryParams);
    }

    /**
     * Invoke.
     * 
     * @param <T> the generic type
     * @param endpoint the endpoint
     * @param httpMethod the http method
     * @param clazz the clazz
     * @return the t
     */
    private <T extends JsonElement> T invoke(String endpoint, String httpMethod, Class<T> clazz) {
        return invoke(endpoint, httpMethod, clazz, null);
    }

    /**
     * Invoke.
     * 
     * @param <T> the generic type
     * @param endpoint the endpoint
     * @param httpMethod the http method
     * @param clazz the clazz
     * @param queryParams the query params
     * @return the t
     */
    @SuppressWarnings("unchecked")
	protected <T extends JsonElement> T invoke(String endpoint, String httpMethod, Class<T> clazz, Map<String, String> queryParams) {
    	WebResource webResource = getClient().resource(getConfig().getBaseUrl() + endpoint);
        if (queryParams != null) {
            for (String key : queryParams.keySet()) {
                webResource = webResource.queryParam(key, queryParams.get(key));
            }
        }
        //webResource = webResource.queryParam("access_token", config.getAccessToken());
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

    /** The Constant PUBLIC_BASE. */
    private static final String PUBLIC_BASE = "/api/fenix/v1/";

    /** The Constant PRIVATE_BASE. */
    private static final String PRIVATE_BASE = PUBLIC_BASE;

    /**
     * Private endpoint.
     * 
     * @param path the path
     * @return the string
     */
    private static final String privateEndpoint(String path) {
        return PRIVATE_BASE + path;
    }

    /**
     * Public endpoint.
     * 
     * @param path the path
     * @return the string
     */
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
    public FenixAbout getAbout() {
        JsonObject json = invoke(publicEndpoint("about"), HttpMethod.GET, JsonObject.class);
        FenixAbout about = gson.fromJson(json, FenixAbout.class);
        return about;
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
    public FenixPerson getPerson(FenixEduUserConfig userConfig) {
        JsonObject json = invokePrivate(privateEndpoint("person"), HttpMethod.GET, JsonObject.class, userConfig);
        System.out.println(json.toString());

        FenixPerson person = gson.fromJson(json, FenixPerson.class);
        return person;
    }
    
    public FenixPerson getPerson() {
    	return getPerson(singleUserConfig);
    }

    /**
     * Obtains information about the curriculum of the person in session.
     * 
     * @return information about the person curriculum
     */
    public FenixCurriculum[] getPersonCurriculum(FenixEduUserConfig userConfig) {
        JsonArray json = invokePrivate(privateEndpoint("person/curriculum"), HttpMethod.GET, JsonArray.class, userConfig);
        FenixCurriculum[] list = gson.fromJson(json, FenixCurriculum[].class);
        return list;
    }
    
    public FenixCurriculum[] getPersonCurriculum() {
    	return getPersonCurriculum(singleUserConfig);
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
    public FenixCalendar getPersonCalendarClasses(CalendarFormat format, FenixEduUserConfig userConfig) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("format", format.toString());
        JsonObject json = invokePrivate(privateEndpoint("person/calendar/classes"), HttpMethod.GET, JsonObject.class, params, userConfig);
        FenixCalendar calendar = gson.fromJson(json, FenixCalendar.class);
        return calendar;
    }
    
    public FenixCalendar getPersonCalendarClasses(CalendarFormat format) {
    	return getPersonCalendarClasses(format, singleUserConfig);
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
    public FenixCalendar getPersonCalendarEvaluations(CalendarFormat format, FenixEduUserConfig userConfig) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("format", format.toString());
        JsonObject json = invokePrivate(privateEndpoint("person/calendar/evaluations"), HttpMethod.GET, JsonObject.class, params, userConfig);
        FenixCalendar calendar = gson.fromJson(json, FenixCalendar.class);
        return calendar;
    }
    
    public FenixCalendar getPersonCalendarEvaluations(CalendarFormat format) {
    	return getPersonCalendarEvaluations(format, singleUserConfig);
    }

    /**
     * Gets the person evaluations.
     * 
     * @return the person evaluations
     */
    public FenixEvaluation[] getPersonEvaluations(FenixEduUserConfig userConfig) {
        JsonArray json = invokePrivate(privateEndpoint("person/evaluations"), HttpMethod.GET, JsonArray.class, userConfig);
        FenixEvaluation[] evaluations = gson.fromJson(json, FenixEvaluation[].class);
        return evaluations;
    }
    
    public FenixEvaluation[] getPersonEvaluations() {
    	return getPersonEvaluations(singleUserConfig);
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
    public FenixEvaluation[] enrollPersonInEvaluation(String evaluationId, EnrolAction action, FenixEduUserConfig userConfig) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("enrol", action.toString());
        JsonArray json =
                invokePrivate(privateEndpoint("person/evaluations/" + evaluationId), HttpMethod.PUT, JsonArray.class, params, userConfig);
        FenixEvaluation[] evaluations = gson.fromJson(json, FenixEvaluation[].class);
        return evaluations;
    }
    
    public FenixEvaluation[] enrollPersonInEvaluation(String evaluationId, EnrolAction action) {
    	return enrollPersonInEvaluation(evaluationId, action, singleUserConfig);
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
    public FenixPayment getPersonPayments(FenixEduUserConfig userConfig) {
        JsonObject json = invokePrivate(privateEndpoint("person/payments"), HttpMethod.GET, JsonObject.class, userConfig);
        FenixPayment payment = gson.fromJson(json, FenixPayment.class);
        return payment;
    }
    
    public FenixPayment getPersonPayments() {
    	return getPersonPayments(singleUserConfig);
    }

    /**
     * Obtains information about the course identified with the given id.
     * 
     * @param courseId the id of the course to obtain information about
     * @return information about the course with the given id.
     */
    public FenixCourse getCourse(String courseId) {
        JsonObject json = invoke(publicEndpoint("courses/" + courseId), HttpMethod.GET, JsonObject.class);
        FenixCourse course = gson.fromJson(json, FenixCourse.class);
        return course;
    }

    /**
     * Obtains the course evaluations for the course identified with the given id.
     * 
     * @param courseId the id of the course to retrieve the course evaluations
     * @return information about the courses evaluations of the given id
     */
    public List<FenixCourseEvaluation> getCourseEvaluations(String courseId) {
        JsonArray json = invoke(publicEndpoint("courses/" + courseId + "/evaluations"), HttpMethod.GET, JsonArray.class);
        List<FenixCourseEvaluation> list = new ArrayList<FenixCourseEvaluation>();

        for (JsonElement jsonElement : json) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();
            if (type.equals("TEST")) {
                list.add(gson.fromJson(jsonObject, FenixCourseEvaluation.Test.class));
            } else if (type.equals("AD_HOC")) {
                list.add(gson.fromJson(jsonObject, FenixCourseEvaluation.AdHocEvaluation.class));
            } else if (type.equals("EXAM")) {
                list.add(gson.fromJson(jsonObject, FenixCourseEvaluation.Exam.class));
            } else if (type.equals("ONLINE_TEST")) {
                list.add(gson.fromJson(jsonObject, FenixCourseEvaluation.OnlineTest.class));
            } else if (type.equals("PROJECT")) {
                list.add(gson.fromJson(jsonObject, FenixCourseEvaluation.Project.class));
            }
        }

        return list;

    }

    /**
     * Obtains information about the course groups for the course with the given id.
     * 
     * @param courseId the id of the course to retrieve the groups information from
     * @return information about course groups
     */
    public FenixCourseGroup[] getCourseGroups(String courseId) {
        JsonArray json = invoke(publicEndpoint("courses/" + courseId + "/groups"), HttpMethod.GET, JsonArray.class);
        FenixCourseGroup[] groups = gson.fromJson(json, FenixCourseGroup[].class);
        return groups;
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
    public FenixSchedule getCourseSchedule(String courseId) {
        JsonObject json = invoke(publicEndpoint("courses/" + courseId + "/schedule"), HttpMethod.GET, JsonObject.class);
        FenixSchedule schedule = gson.fromJson(json, FenixSchedule.class);
        return schedule;
    }

    /**
     * Obtains the student list for the course identified by the given id.
     * 
     * @param courseId the id of the course to obtain the student list
     * @return the list of course students
     */
    public FenixCourseStudents getCourseStudents(String courseId) {
        JsonObject json = invoke(publicEndpoint("courses/" + courseId + "/students"), HttpMethod.GET, JsonObject.class);
        FenixCourseStudents students = gson.fromJson(json, FenixCourseStudents.class);
        return students;
    }

    /**
     * Obtains an array containing both campus spaces.
     * 
     * @return the array of both campus spaces.
     */
    public FenixSpace[] getSpaces() {
        JsonArray json = invoke(publicEndpoint("spaces"), HttpMethod.GET, JsonArray.class);
        FenixSpace[] spaces = gson.fromJson(json, FenixSpace[].class);
        return spaces;
    }

    /**
     * Obtains space information regarding space type (Campus, Building, Floor
     * or Room).
     * 
     * @param <T> the generic type
     * @param spaceId the id of the space
     * @param day the day for which the events should be listed for that room
     *            (e.g. "dd/mm/yyyy")
     * @param clazz the class of return type. Must extend FenixSpace
     *            (e.g. FenixSpace.Campus.class)
     * @return information regarding the space at a particular day
     */
    public <T extends FenixSpace> T getSpace(String spaceId, String day, Class<T> clazz) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("day", day);
        JsonObject json = invoke(publicEndpoint("spaces/" + spaceId), HttpMethod.GET, JsonObject.class, params);

        T space = gson.fromJson(json, clazz);
        return space;
    }

    /**
     * Obtains the degrees associated to a particular execution year.
     * 
     * @param year the representative string of the execution year you wish to
     *            retrieve the courses from (e.g. 2003/2004)
     * @return a JsonArray describing the courses
     */
    public FenixDegree[] getDegrees(String year) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("year", year);
        JsonArray json = invoke(publicEndpoint("degrees"), HttpMethod.GET, JsonArray.class, params);
        FenixDegree[] degrees = gson.fromJson(json, FenixDegree[].class);
        return degrees;
    }

    /**
     * Retrieves information about the degree with the given id, in a particular execution
     * year.
     * 
     * <p>
     * <b>Scope:</b> Public
     * </p>
     * 
     * @param degreeId the degree id
     * @param year the execution year to retrieve the information of the degree
     * @return the degree
     */
    public FenixDegree getDegree(String degreeId, String year) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("year", year);
        JsonObject json = invoke(publicEndpoint("degrees/" + degreeId), HttpMethod.GET, JsonObject.class, params);
        FenixDegree degree = gson.fromJson(json, FenixDegree.class);
        return degree;
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
     * @return the degree courses
     */
    public FenixExecutionCourse[] getDegreeCourses(String degreeId, String year) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("year", year);
        JsonArray json = invoke(publicEndpoint("degrees/" + degreeId + "/courses"), HttpMethod.GET, JsonArray.class);
        FenixExecutionCourse[] degreeCourses = gson.fromJson(json, FenixExecutionCourse[].class);

        return degreeCourses;
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
    public FenixPersonCourses getPersonCourses(String academicTerm, FenixEduUserConfig userConfig) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("academicTerm", academicTerm);
        JsonObject json = invokePrivate(privateEndpoint("person/courses"), HttpMethod.GET, JsonObject.class, params, userConfig);
        FenixPersonCourses courses = gson.fromJson(json, FenixPersonCourses.class);
        return courses;
    }
    
    public FenixPersonCourses getPersonCourses(String academicTerm) {
    	return getPersonCourses(academicTerm, singleUserConfig);
    }

    /**
     * Gets the config.
     * 
     * @return the config
     */
    public FenixEduConfig getConfig() {
        return config;
    }

	public Client getClient() {
		return client;
	}
    
    
}
