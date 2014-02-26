package org.fenixedu.sdk.api;

import org.fenixedu.sdk.exception.FenixEduClientException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface PublicResources {

    JsonObject getAbout() throws FenixEduClientException;

    JsonObject getDomainModel() throws FenixEduClientException;

    JsonObject getAcademicTerms() throws FenixEduClientException;

    JsonObject getCourse(String courseId) throws FenixEduClientException;

    JsonArray getCourseEvaluations(String courseId) throws FenixEduClientException;

    JsonArray getCourseGroups(String courseId) throws FenixEduClientException;

    JsonObject getCourseSchedule(String courseId) throws FenixEduClientException;

    JsonObject getCourseStudents(String courseId) throws FenixEduClientException;

    JsonArray getDegrees() throws FenixEduClientException;

    JsonArray getDegrees(String academicTerm) throws FenixEduClientException;

    JsonObject getDegree(String degreeId) throws FenixEduClientException;

    JsonObject getDegree(String degreeId, String academicTerm) throws FenixEduClientException;

    JsonArray getDegreeCourses(String degreeId) throws FenixEduClientException;

    JsonArray getDegreeCourses(String degreeId, String academicTerm) throws FenixEduClientException;

    JsonArray getSpaces() throws FenixEduClientException;

    JsonObject getSpace(String spaceId, String day) throws FenixEduClientException;

    byte[] getSpaceBlueprint(String spaceId) throws FenixEduClientException;
}
