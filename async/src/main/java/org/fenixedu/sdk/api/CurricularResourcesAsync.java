package org.fenixedu.sdk.api;

import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

public interface CurricularResourcesAsync {

    void getPersonCourses() throws FenixEduClientException;

    void getPersonCourses(String academicTerm) throws FenixEduClientException;

    void getPersonCurriculum() throws FenixEduClientException;

}
