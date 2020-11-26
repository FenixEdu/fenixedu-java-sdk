package org.fenixedu.sdk.api;

import org.fenixedu.sdk.Authorization;

import com.google.gson.JsonArray;

public interface DegreeCurricularManagementResources {

    JsonArray getPhdThesisProcesses(Authorization authorization, String academicTerm, String department, String username);

}
