package org.fenixedu.sdk.android;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonArray;

/**
 * This endpoint returns the informations for the PhD Thesis processes associated to a
 * particular academic term, department or student.
 * If no academicTerm is defined it returns the information for the currentAcademicTerm.
 * If no department is defined it returns the information for all departments.
 * If no student username is defined it returns the information for all students. Otherwise,
 * all other parameters are ignored and it returns information for that specific student.
 */
public class GetPhdThesisProcessesAsyncTask extends AuthorizedFenixEduAsyncTask<String, JsonArray> {

    public GetPhdThesisProcessesAsyncTask(FenixEduClient client, Authorization authorization) {
        super(client, authorization);
    }

    public GetPhdThesisProcessesAsyncTask(FenixEduClient client, PostExecuteCallback postExecuteCallback, Authorization authorization) {
        super(client, postExecuteCallback, authorization);
    }

    @Override
    protected JsonArray executeInBackground(String... params) {
        if (params == null || params.length == 0) {
            return getClient().degreeCurricularManagementScope().getPhdThesisProcesses(getAuthorization(), null, null, null);
        } else if (params.length == 1) {
            return getClient().degreeCurricularManagementScope().getPhdThesisProcesses(getAuthorization(), params[0], null, null);
        } else if (params.length == 2) {
            return getClient().degreeCurricularManagementScope().getPhdThesisProcesses(getAuthorization(), params[0], params[1], null);
        } else {
            return getClient().degreeCurricularManagementScope().getPhdThesisProcesses(getAuthorization(), params[0], params[1], params[2]);
        }
    }
}
