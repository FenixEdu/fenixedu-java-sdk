package org.fenixedu.sdk.android;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonArray;

public class GetPersonEvaluationsAsyncTask extends AuthorizedFenixEduAsyncTask<Void, JsonArray> {

    public GetPersonEvaluationsAsyncTask(FenixEduClient client, Authorization authorization) {
        super(client, authorization);
    }

    @Override
    protected JsonArray doInBackground(Void... params) {
        return getClient().getPersonEvaluations(getAuthorization());
    }
}
