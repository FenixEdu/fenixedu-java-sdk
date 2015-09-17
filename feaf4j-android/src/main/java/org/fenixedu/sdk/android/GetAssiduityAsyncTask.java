package org.fenixedu.sdk.android;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonObject;

public class GetAssiduityAsyncTask extends AuthorizedFenixEduAsyncTask<String, JsonObject> {

    public GetAssiduityAsyncTask(FenixEduClient client, Authorization authorization) {
        super(client, authorization);
    }

    public GetAssiduityAsyncTask(FenixEduClient client, PostExecuteCallback postExecuteCallback, Authorization authorization) {
        super(client, postExecuteCallback, authorization);
    }

    @Override
    protected JsonObject executeInBackground(String... params) {
        if (params != null && params.length == 1) {
            return getClient().assiduityScope().getAssiduity(getAuthorization(), params[0]);
        }
        return new JsonObject();
    }
}
