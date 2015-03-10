package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonObject;

/**
 * This endpoint returns the information for parking.
 *
 */
public class GetParkingAsyncTask extends FenixEduAsyncTask<Void, JsonObject> {

    public GetParkingAsyncTask(FenixEduClient client) {
        super(client);
    }

    public GetParkingAsyncTask(FenixEduClient client, PostExecuteCallback postExecuteCallback) {
        super(client, postExecuteCallback);
    }

    @Override
    protected JsonObject executeInBackground(Void... params) {
        return getClient().publicScope().getParking();
    }

}
