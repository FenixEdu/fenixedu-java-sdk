package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonObject;

public class GetSpaceAsyncTask extends FenixEduAsyncTask<String, JsonObject> {

    public GetSpaceAsyncTask(FenixEduClient client) {
        super(client);
    }

    public GetSpaceAsyncTask(FenixEduClient client, PostExecuteCallback postExecuteCallback) {
        super(client, postExecuteCallback);
    }

    @Override
    protected JsonObject executeInBackground(String... params) {
        if (params.length == 1) {
            return getClient().publicScope().getSpace(params[0]);
        } else if (params.length == 2) {
            return getClient().publicScope().getSpace(params[0], params[1]);
        }
        return new JsonObject();
    }
}
