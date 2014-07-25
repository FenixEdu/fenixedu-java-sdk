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

    @Override
    protected T doInBackground(P... params) {
        try {
            return executeInBackground(params);
        } catch (Throwable e) {
            if (postExecuteCallback != null) {
                postExecuteCallback.onException(e);
            }
        }
        return null;
    }

    protected abstract T executeInBackground(P... params);

    @SuppressWarnings("unchecked")
    public final void execute(PostExecuteCallback<T> postExecuteCallback, P... params) {
        this.postExecuteCallback = postExecuteCallback;
        this.execute(params);
    }

    @Override
    protected void onPostExecute(T result) {
        super.onPostExecute(result);
        if (postExecuteCallback != null && result != null) {
            postExecuteCallback.onPostExecute(result);
        }
    }
}
