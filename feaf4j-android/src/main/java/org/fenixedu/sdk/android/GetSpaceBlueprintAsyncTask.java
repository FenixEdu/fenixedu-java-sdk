package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

public class GetSpaceBlueprintAsyncTask extends FenixEduAsyncTask<String, byte[]> {

    public GetSpaceBlueprintAsyncTask(FenixEduClient client) {
        super(client);
    }

    @Override
    protected byte[] doInBackground(String... params) {
        return getClient().publicScope().getSpaceBlueprint(params[0]);
    }
}
