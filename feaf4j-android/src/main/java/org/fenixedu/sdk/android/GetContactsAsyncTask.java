package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonArray;

/**
 * This endpoint returns the information for contacts.
 *
 */
public class GetContactsAsyncTask extends FenixEduAsyncTask<Void, JsonArray> {

    public GetContactsAsyncTask(FenixEduClient client) {
        super(client);
    }

    public GetContactsAsyncTask(FenixEduClient client, PostExecuteCallback postExecuteCallback) {
        super(client, postExecuteCallback);
    }

    @Override
    protected JsonArray executeInBackground(Void... params) {
        return getClient().publicScope().getContacts();
    }

}
