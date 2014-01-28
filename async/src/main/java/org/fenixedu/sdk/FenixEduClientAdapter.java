package org.fenixedu.sdk;

import pt.ist.fenixedu.sdk.api.FenixEduEndpoint;
import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class FenixEduClientAdapter implements FenixEduClientListener {

    @Override
    public void gotAbout(JsonObject about) {
    }

    @Override
    public void gotAcademicTerms(JsonObject academicTerms) {
    }

    @Override
    public void gotCourse(JsonObject course) {
    }

    @Override
    public void gotCourseEvaluations(JsonArray courseEvaluations) {
    }

    @Override
    public void gotCourseGroups(JsonArray courseGroups) {
    }

    @Override
    public void gotCourseSchedule(JsonObject courseSchedule) {
    }

    @Override
    public void gotCourseStudents(JsonObject courseStudents) {
    }

    @Override
    public void gotDegrees(JsonArray degrees) {
    }

    @Override
    public void gotDegree(JsonObject degree) {

    }

    @Override
    public void gotDegreeCourses(JsonArray degreeCourses) {
    }

    @Override
    public void gotPerson(JsonObject person) {
    }

    @Override
    public void gotPersonCalendarClasses(JsonObject personCalendarClasses) {
    }

    @Override
    public void gotPersonCalendarEvaluations(JsonObject personCalendarEvaluations) {
    }

    @Override
    public void gotPersonCourses(JsonObject personCourses) {
    }

    @Override
    public void gotPersonCurriculum(JsonArray personCurriculum) {
    }

    @Override
    public void gotPersonEvaluations(JsonArray personEvaluations) {
    }

    @Override
    public void gotPersonEvaluationEnrollment(JsonArray evaluationEnrolment) {
    }

    @Override
    public void gotSpaces(JsonArray spaces) {
    }

    @Override
    public void gotSpace(JsonObject space) {
    }

    @Override
    public void gotSpaceBlueprint(byte[] image) {

    }

    @Override
    public void onException(FenixEduClientException te, FenixEduEndpoint endpoint) {
    }

    @Override
    public void gotPersonPayments(JsonObject personPayments) {
    }

}
