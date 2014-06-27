package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import android.os.AsyncTask;

public abstract class FenixEduAsyncTask<P, T> extends AsyncTask<P, Void, T> {

    private final FenixEduClient client;

    private PostExecuteCallback<T> postExecuteCallback;

    public FenixEduAsyncTask(FenixEduClient client) {
        this.client = client;
    }

    public FenixEduClient getClient() {
        return client;
    }
    
    @SuppressWarnings("unchecked")
    public final void execute(PostExecuteCallback<T> postExecuteCallback, P... params) {
        this.postExecuteCallback = postExecuteCallback;
        this.execute(params);
    }

    @Override
    protected void onPostExecute(T result) {
        super.onPostExecute(result);
        postExecuteCallback.onPostExecute(result);
    }
}
