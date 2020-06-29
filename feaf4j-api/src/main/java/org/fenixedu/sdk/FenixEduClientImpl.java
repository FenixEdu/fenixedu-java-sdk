package org.fenixedu.sdk;

import java.util.HashMap;
import java.util.Map;

import org.fenixedu.sdk.api.AssiduityResources;
import org.fenixedu.sdk.api.CurricularResources;
import org.fenixedu.sdk.api.EvaluationResources;
import org.fenixedu.sdk.api.FenixEduEndpoint;
import org.fenixedu.sdk.api.PaymentResources;
import org.fenixedu.sdk.api.PersonalResources;
import org.fenixedu.sdk.api.PublicResources;
import org.fenixedu.sdk.api.ScheduleResources;
import org.fenixedu.sdk.domain.CalendarFormat;
import org.fenixedu.sdk.domain.EnrolAction;
import org.fenixedu.sdk.exception.ApiClientException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class FenixEduClientImpl extends FenixEduClientBaseImpl implements FenixEduClient {

    public FenixEduClientImpl(ApplicationConfiguration config) {
        super(config);
    }

    @Override
    public PublicResources publicScope() {
        return this;
    }

    @Override
    public AssiduityResources assiduityScope() {
        return this;
    }

    @Override
    public PersonalResources personalScope() {
        return this;
    }

    @Override
    public ScheduleResources scheduleScope() {
        return this;
    }

    @Override
    public CurricularResources curricularScope() {
        return this;
    }

    @Override
    public EvaluationResources evaluationScope() {
        return this;
    }

    @Override
    public PaymentResources paymentScope() {
        return this;
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
     * @throws ApiClientException description
     */
    @Override
    public JsonObject getAbout() {
        return invoke(FenixEduEndpoint.ABOUT);
    }

    /**
     * Obtains the personal assiduity.
     *
     * <p>
     * <b>Scope:</b> Assiduity
     * </p>
     *
     *
     * @return information about personal assiduity
     * @throws ApiClientException description
     */
    @Override
    public JsonObject getAssiduity(Authorization authorization, String date) {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("date", date);
        return invoke(FenixEduEndpoint.ASSIDUITY, authorization, queryParams);
    }

    /**
     * Obtains information about the person in context.
     *
     * <p>
     * <b>Scope:</b> Info
     * </p>
     *
     * @return information about the person
     * @throws FenixEduClientException .
     */
    @Override
    public JsonObject getPerson(Authorization authorization) {
        return invoke(FenixEduEndpoint.PERSON, authorization);
    }

    /**
     * Obtains information about the curriculum of the person in session.
     *
     * @return information about the person curriculum
     * @throws FenixEduClientException .
     */
    @Override
    public JsonArray getPersonCurriculum(Authorization authorization) {
        return invoke(FenixEduEndpoint.PERSON_CURRICULUM, authorization);
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
     * @throws FenixEduClientException .
     */
    @Override
    public JsonObject getPersonCalendarClasses(Authorization authorization, CalendarFormat format) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("format", format.toString());
        return invoke(FenixEduEndpoint.PERSON_CALENDAR_CLASSES, authorization, params);
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
     * @throws FenixEduClientException .
     */
    @Override
    public JsonObject getPersonCalendarEvaluations(Authorization authorization, CalendarFormat format) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("format", format.toString());
        return invoke(FenixEduEndpoint.PERSON_CALENDAR_EVALUATIONS, authorization, params);
    }

    @Override
    public JsonArray getPersonEvaluations(Authorization authorization) {
        return invoke(FenixEduEndpoint.PERSON_EVALUATIONS, authorization);
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
     * @throws FenixEduClientException .
     */
    @Override
    public JsonArray enrollPersonInEvaluation(Authorization authorization, String evaluationId, EnrolAction action) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("enrol", action.toString());
        return invoke(FenixEduEndpoint.PERSON_EVALUATION, authorization, params, evaluationId);
    }

    /**
     * Obtains the payments for the current person in context.
     *
     * <p>
     * <b>Scope:</b> Personal
     * </p>
     *
     * @return the payments of the person.
     * @throws FenixEduClientException .
     */
    @Override
    public JsonObject getPersonPayments(Authorization authorization) {
        return invoke(FenixEduEndpoint.PERSON_PAYMENTS, authorization);
    }

    /**
     * Obtains information about the course identified with the given id.
     *
     * @param courseId the id of the course to obtain information about
     * @return information about the course with the given id.
     * @throws FenixEduClientException .
     */
    @Override
    public JsonObject getCourse(String courseId) {
        return invoke(FenixEduEndpoint.COURSE, courseId);
    }

    /**
     * Obtains the course evaluations for the course identified with the given id
     *
     * @param courseId the id of the course to retrieve the course evaluations
     * @return information about the courses evaluations of the given id
     * @throws FenixEduClientException .
     */
    @Override
    public JsonArray getCourseEvaluations(String courseId) {
        return invoke(FenixEduEndpoint.COURSE_EVALUATIONS, courseId);
    }

    /**
     * Obtains information about the course groups for the course with the given id.
     *
     * @param courseId the id of the course to retrieve the groups information from
     * @return information about course groups
     * @throws FenixEduClientException .
     */
    @Override
    public JsonArray getCourseGroups(String courseId) {
        return invoke(FenixEduEndpoint.COURSE_GROUPS, courseId);
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
     * @throws FenixEduClientException .
     */
    @Override
    public JsonObject getCourseSchedule(String courseId) {
        return invoke(FenixEduEndpoint.COURSE_SCHEDULE, courseId);
    }

    /**
     * Obtains the student list for the course identified by the given id.
     *
     * @param courseId the id of the course to obtain the student list
     * @return the list of course students
     * @throws FenixEduClientException .
     */
    @Override
    public JsonObject getCourseStudents(String courseId) {
        return invoke(FenixEduEndpoint.COURSE_STUDENTS, courseId);
    }

    /**
     * Obtains an array containing both campus spaces.
     *
     * @return the array of both campus spaces.
     * @throws FenixEduClientException .
     */
    @Override
    public JsonArray getSpaces() {
        return invoke(FenixEduEndpoint.SPACES);
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
     * @throws FenixEduClientException .
     */
    @Override
    public JsonObject getSpace(String spaceId, String day) {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("day", day);
        return invoke(FenixEduEndpoint.SPACE, queryParams, spaceId);
    }

    /**
     * Obtains space information regarding space type (Campus, Building, Floor
     * or Room)
     *
     * @param spaceId
     *            the id of the space
     *
     *
     * @return information regarding the space at a particular day
     * @throws FenixEduClientException .
     */
    @Override
    public JsonObject getSpace(String spaceId) {
        return invoke(FenixEduEndpoint.SPACE, spaceId);
    }

    /**
     * Obtains the degrees associated to the current academic term.
     *
     * @return a JsonArray describing the courses of the current academic term.
     */
    @Override
    public JsonArray getDegrees() {
        return invoke(FenixEduEndpoint.DEGREES);
    }

    /**
     * Obtains the canteen information.
     *
     * @return a JsonObject with the canteen information.
     */
    @Override
    public JsonArray getCanteen() {
        return invoke(FenixEduEndpoint.CANTEEN);
    }

    /**
     * Obtains the canteen information.
     *
     * @return a JsonObject with the canteen information.
     */
    @Override
    public JsonArray getCanteen(String day) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("day", day);
        return invoke(FenixEduEndpoint.CANTEEN, params);
    }

    /**
     * Obtains the contacts information.
     *
     * @return a JsonObject with the contacts information.
     */
    @Override
    public JsonArray getContacts() {
        return invoke(FenixEduEndpoint.CONTACTS);
    }

    /**
     * Obtains the parking information.
     *
     * @return a JsonObject with the parking information.
     */
    @Override
    public JsonObject getParking() {
        return invoke(FenixEduEndpoint.PARKING);
    }

    /**
     * Obtains the shuttle information.
     *
     * @return a JsonObject with the shuttle information.
     */
    @Override
    public JsonObject getShuttle() {
        return invoke(FenixEduEndpoint.SHUTTLE);
    }

    /**
     * Obtains the degrees associated to a particular academic term.
     *
     * @param academicTerm
     *            the representative string of the execution year you wish to
     *            retrieve the courses from (e.g. 2003/2004)
     * @return a JsonArray describing the courses for the provided academic term.
     * @throws FenixEduClientException .
     */
    @Override
    public JsonArray getDegrees(String academicTerm) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("academicTerm", academicTerm);
        return invoke(FenixEduEndpoint.DEGREES, params);
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
     * @param academicTerm
     *            the academic term to retrieve the information of the degree
     * @return degree
     * @throws FenixEduClientException .
     */
    @Override
    public JsonObject getDegree(String degreeId, String academicTerm) {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("academicTerm", academicTerm);
        return invoke(FenixEduEndpoint.DEGREE, queryParams, degreeId);
    }

    /**
     * Retrieves information about the degree courses, in a particular academic term.
     *
     * <p>
     * <b>Scope:</b> Public
     * </p>
     *
     * @param degreeId the id of the degree
     * @return a JsonArray with all the degree courses within the specified academic term.
     * @throws FenixEduClientException .
     */
    @Override
    public JsonArray getDegreeCourses(String degreeId) {
        return invoke(FenixEduEndpoint.DEGREE_COURSES, degreeId);
    }

    /**
     * Retrieves information about the degree courses, in a particular academic term.
     *
     * <p>
     * <b>Scope:</b> Public
     * </p>
     *
     * @param degreeId the id of the degree
     * @param academicTerm the execution year (e.g. 2010/2011)
     * @return a JsonArray with all the degree courses within the specified academic term.
     * @throws FenixEduClientException .
     */
    @Override
    public JsonArray getDegreeCourses(String degreeId, String academicTerm) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("academicTerm", academicTerm);
        return invoke(FenixEduEndpoint.DEGREE_COURSES, params, degreeId);
    }

    /**
     * Obtains the courses of the user in context for the current academic term.
     *
     * <p>
     * <b>Scope:</b> Enrollments
     * </p>
     *
     * @return both the courses that the person is teaching and/or enrolled on.
     * @throws FenixEduClientException .
     */
    @Override
    public JsonObject getPersonCourses(Authorization authorization) {
        return invoke(FenixEduEndpoint.PERSON_COURSES, authorization);
    }

    /**
     * Obtains the courses of the user in context for a particular execution
     * year and semester.
     *
     * <p>
     * <b>Scope:</b> Enrollments
     * </p>
     *
     * @param academicTerm
     *            the academic term (e.g. "2003/2004")
     * @return both the courses that the person is teaching or enrolled on.
     * @throws FenixEduClientException .
     */
    @Override
    public JsonObject getPersonCourses(Authorization authorization, String academicTerm) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("academicTerm", academicTerm);
        return invoke(FenixEduEndpoint.PERSON_COURSES, authorization, params);
    }

    @Override
    public JsonObject getAcademicTerms() {
        return invoke(FenixEduEndpoint.ACADEMIC_TERMS);
    }

    @Override
    public JsonObject getDegree(String degreeId) {
        return invoke(FenixEduEndpoint.DEGREE, degreeId);
    }

    @Override
    public byte[] getSpaceBlueprint(String spaceId) {
        return invoke(FenixEduEndpoint.SPACE_BLUEPRINT, spaceId);
    }

    @Override
    public JsonObject getDomainModel() {
        return invoke(FenixEduEndpoint.DOMAIN_MODEL);
    }
}
