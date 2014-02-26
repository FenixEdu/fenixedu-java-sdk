package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonObject;

public class GetSpacesAsyncTask extends FenixEduAsyncTask<Void, JsonObject> {

    public GetSpacesAsyncTask(FenixEduClient client) {
        super(client);
    }

    @Override
    protected JsonObject doInBackground(Void... params) {
        return getClient().publicScope().getAcademicTerms();
    }

}
