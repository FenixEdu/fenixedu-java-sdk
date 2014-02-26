package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import android.os.AsyncTask;

public abstract class FenixEduAsyncTask<P, T> extends AsyncTask<P, Void, T> {

    private final FenixEduClient client;

    public FenixEduAsyncTask(FenixEduClient client) {
        this.client = client;
    }

    public FenixEduClient getClient() {
        return client;
    }
}
