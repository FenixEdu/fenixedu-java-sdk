package org.fenixedu.sdk.api;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.exception.FenixEduClientException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface CurricularResources {

    JsonObject getPersonCourses(Authorization authorization) throws FenixEduClientException;

    JsonObject getPersonCourses(Authorization authorization, String academicTerm) throws FenixEduClientException;

    JsonArray getPersonCurriculum(Authorization authorization) throws FenixEduClientException;

}
