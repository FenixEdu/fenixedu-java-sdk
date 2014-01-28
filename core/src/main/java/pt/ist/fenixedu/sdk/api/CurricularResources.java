package pt.ist.fenixedu.sdk.api;

import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface CurricularResources {

    JsonObject getPersonCourses() throws FenixEduClientException;

    JsonObject getPersonCourses(String academicTerm) throws FenixEduClientException;

    JsonArray getPersonCurriculum() throws FenixEduClientException;

}
