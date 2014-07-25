package org.fenixedu.sdk.android;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonObject;

/**
 * This endpoint returns the user's course information.
 */
public class GetPersonCoursesAsyncTask extends AuthorizedFenixEduAsyncTask<String, JsonObject> {

    public GetPersonCoursesAsyncTask(FenixEduClient client, Authorization authorization) {
        super(client, authorization);
    }

    @Override
    protected JsonObject executeInBackground(String... params) {
        if (params.length == 0) {
            return getClient().getPersonCourses(getAuthorization());
        } else {
            return getClient().getPersonCourses(getAuthorization(), params[0]);
        }
    }
}
