package org.fenixedu.sdk;

import pt.ist.fenixedu.sdk.api.FenixEduEndpoint;
import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface FenixEduClientListener {

    void gotAbout(JsonObject about);

    void gotAcademicTerms(JsonObject academicTerms);

    void gotCourse(JsonObject course);

    void gotCourseEvaluations(JsonArray courseEvaluations);

    void gotCourseGroups(JsonArray courseGroups);

    void gotCourseSchedule(JsonObject courseSchedule);

    void gotCourseStudents(JsonObject courseStudents);

    void gotDegrees(JsonArray degrees);

    void gotDegree(JsonObject degree);

    void gotDegreeCourses(JsonArray degreeCourses);

    void gotPerson(JsonObject person);

    void gotPersonCalendarClasses(JsonObject personCalendarClasses);

    void gotPersonCalendarEvaluations(JsonObject personCalendarEvaluations);

    void gotPersonCourses(JsonObject personCourses);

    void gotPersonCurriculum(JsonArray personCurriculum);

    void gotPersonEvaluations(JsonArray personEvaluations);

    void gotPersonEvaluationEnrollment(JsonArray personEvaluations);

    void gotPersonPayments(JsonObject personPayments);

    void gotSpaces(JsonArray spaces);

    void gotSpace(JsonObject space);

    void gotSpaceBlueprint(byte[] image);

    void onException(FenixEduClientException te, FenixEduEndpoint endpoint);

}
