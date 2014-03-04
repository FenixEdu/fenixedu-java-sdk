package org.fenixedu.sdk.android;

import org.fenixedu.sdk.ApplicationConfiguration;
import org.fenixedu.sdk.FenixEduClient;
import org.fenixedu.sdk.FenixEduClientFactory;

import android.os.AsyncTask;

public class GetFenixEduClientAsyncTask extends AsyncTask<Void, Void, FenixEduClient> {

    private ApplicationConfiguration config;

    public GetFenixEduClientAsyncTask(ApplicationConfiguration config) {
        this.config = config;
    }

    @Override
    protected FenixEduClient doInBackground(Void... params) {
        return FenixEduClientFactory.getAndDefineSingletonFromApplicationConfiguration(config);
    }
}
