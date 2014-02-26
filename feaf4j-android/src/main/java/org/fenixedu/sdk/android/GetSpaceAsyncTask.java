package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonObject;

public class GetSpaceAsyncTask extends FenixEduAsyncTask<String, JsonObject> {

    public GetSpaceAsyncTask(FenixEduClient client) {
        super(client);
    }

    @Override
    protected JsonObject doInBackground(String... params) {
        return getClient().publicScope().getSpace(params[0], params[1]);
    }

}
