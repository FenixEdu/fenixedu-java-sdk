package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

public class GetSpaceBlueprintAsyncTask extends FenixEduAsyncTask<String, byte[]> {

    public GetSpaceBlueprintAsyncTask(FenixEduClient client) {
        super(client);
    }

    public GetSpaceBlueprintAsyncTask(FenixEduClient client, PostExecuteCallback postExecuteCallback) {
        super(client, postExecuteCallback);
    }

    @Override
    protected byte[] executeInBackground(String... params) {
        return getClient().publicScope().getSpaceBlueprint(params[0]);
    }
}
