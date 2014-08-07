package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonObject;

public class GetDomainModelAsyncTask extends FenixEduAsyncTask<Void, JsonObject> {

    public GetDomainModelAsyncTask(FenixEduClient client) {
        super(client);
    }

    public GetDomainModelAsyncTask(FenixEduClient client, PostExecuteCallback postExecuteCallback) {
        super(client, postExecuteCallback);
    }

    @Override
    protected JsonObject executeInBackground(Void... params) {
        return getClient().publicScope().getDomainModel();
    }

}
