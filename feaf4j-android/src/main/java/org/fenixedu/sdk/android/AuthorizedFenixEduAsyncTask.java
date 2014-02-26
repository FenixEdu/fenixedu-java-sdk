package org.fenixedu.sdk.android;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.FenixEduClient;

public abstract class AuthorizedFenixEduAsyncTask<P, T> extends FenixEduAsyncTask<P, T> {

    private final Authorization authorization;

    public AuthorizedFenixEduAsyncTask(FenixEduClient client, Authorization authorization) {
        super(client);
        this.authorization = authorization;
    }

    public Authorization getAuthorization() {
        return this.authorization;
    }

}
