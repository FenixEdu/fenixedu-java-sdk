package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonArray;

/**
 * This endpoint returns all the academic terms available to be used as a query parameter in other endpoints.
 * The returned object keys are not ordered in any particular way.
 */
public class GetAcademicTermsAsyncTask extends FenixEduAsyncTask<Void, JsonArray> {

    public GetAcademicTermsAsyncTask(FenixEduClient client) {
        super(client);
    }

    @Override
    protected JsonArray executeInBackground(Void... params) {
        return getClient().publicScope().getSpaces();
    }

}
