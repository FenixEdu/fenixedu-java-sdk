package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonObject;

/**
 * This endpoint returns the information for shuttle.
 *
 */
public class GetShuttleAsyncTask extends FenixEduAsyncTask<Void, JsonObject> {

    public GetShuttleAsyncTask(FenixEduClient client) {
        super(client);
    }

    public GetShuttleAsyncTask(FenixEduClient client, PostExecuteCallback postExecuteCallback) {
        super(client, postExecuteCallback);
    }

    @Override
    protected JsonObject executeInBackground(Void... params) {
        return getClient().publicScope().getShuttle();
    }

}
