package org.fenixedu.sdk.api;

import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

public interface PublicResourcesAsync {

    void getAbout() throws FenixEduClientException;

    void getAcademicTerms() throws FenixEduClientException;

    void getCourse(String courseId) throws FenixEduClientException;

    void getCourseEvaluations(String courseId) throws FenixEduClientException;

    void getCourseGroups(String courseId) throws FenixEduClientException;

    void getCourseSchedule(String courseId) throws FenixEduClientException;

    void getCourseStudents(String courseId) throws FenixEduClientException;

    void getDegrees() throws FenixEduClientException;

    void getDegrees(String academicTerm) throws FenixEduClientException;

    void getDegree(String degreeId) throws FenixEduClientException;

    void getDegree(String degreeId, String academicTerm) throws FenixEduClientException;

    void getDegreeCourses(String degreeId) throws FenixEduClientException;

    void getDegreeCourses(String degreeId, String academicTerm) throws FenixEduClientException;

    void getSpaces() throws FenixEduClientException;

    void getSpace(String spaceId, String day) throws FenixEduClientException;

    void getSpaceBlueprint(String spaceId) throws FenixEduClientException;

}
