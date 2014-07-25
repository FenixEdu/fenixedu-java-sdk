package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonObject;

public class GetDomainModelAsyncTask extends FenixEduAsyncTask<Void, JsonObject> {

    public GetDomainModelAsyncTask(FenixEduClient client) {
        super(client);
    }

    @Override
    protected JsonObject executeInBackground(Void... params) {
        return getClient().publicScope().getDomainModel();
    }

}
