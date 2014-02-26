package org.fenixedu.sdk.android;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonObject;

/**
 * This endpoint allows to access the current person information.
 */
public class GetPersonAsyncTask extends AuthorizedFenixEduAsyncTask<Void, JsonObject> {

    public GetPersonAsyncTask(FenixEduClient client, Authorization authorization) {
        super(client, authorization);
    }

    @Override
    protected JsonObject doInBackground(Void... params) {
        return getClient().getPerson(getAuthorization());
    }
}
