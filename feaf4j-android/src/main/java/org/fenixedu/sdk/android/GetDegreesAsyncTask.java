package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonArray;

/**
 * This endpoint returns the information for all degrees.
 * If no academicTerm is defined it returns the degree information for the currentAcademicTerm.
 */
public class GetDegreesAsyncTask extends FenixEduAsyncTask<Void, JsonArray> {

    public GetDegreesAsyncTask(FenixEduClient client) {
        super(client);
    }

    @Override
    protected JsonArray doInBackground(Void... params) {
        return getClient().publicScope().getDegrees();
    }

}
