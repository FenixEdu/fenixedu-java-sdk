package org.fenixedu.sdk.api;

import org.fenixedu.sdk.Authorization;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface CurricularResources {

    JsonObject getPersonCourses(Authorization authorization);

    JsonObject getPersonCourses(Authorization authorization, String academicTerm);

    JsonArray getPersonCurriculum(Authorization authorization);

}
