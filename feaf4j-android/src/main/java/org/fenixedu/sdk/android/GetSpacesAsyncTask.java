package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonArray;

public class GetSpacesAsyncTask extends FenixEduAsyncTask<Void, JsonArray> {

    public GetSpacesAsyncTask(FenixEduClient client) {
        super(client);
    }

    public GetSpacesAsyncTask(FenixEduClient client, PostExecuteCallback postExecuteCallback) {
        super(client, postExecuteCallback);
    }

    @Override
    protected JsonArray executeInBackground(Void... params) {
        return getClient().publicScope().getSpaces();
    }

}
