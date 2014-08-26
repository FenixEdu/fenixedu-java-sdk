package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonArray;

/**
 * This endpoint returns the information for canteen.
 *
 */
public class GetCanteenAsyncTask extends FenixEduAsyncTask<String, JsonArray> {

    public GetCanteenAsyncTask(FenixEduClient client) {
        super(client);
    }

    public GetCanteenAsyncTask(FenixEduClient client, PostExecuteCallback postExecuteCallback) {
        super(client, postExecuteCallback);
    }

    @Override
    protected JsonArray executeInBackground(String... params) {
        return getClient().publicScope().getCanteen(params[0]);
    }

}
