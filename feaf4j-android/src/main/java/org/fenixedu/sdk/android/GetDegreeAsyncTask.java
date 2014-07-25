package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonObject;

/**
 * This endpoint returns the information for the degree identified by the provided id.
 * If no academicTerm is defined it returns the degree information for the currentAcademicTerm.
 */
public class GetDegreeAsyncTask extends FenixEduAsyncTask<String, JsonObject> {

    public GetDegreeAsyncTask(FenixEduClient client) {
        super(client);
    }

    @Override
    protected JsonObject executeInBackground(String... params) {
        return getClient().publicScope().getDegree(params[0]);
    }
}
