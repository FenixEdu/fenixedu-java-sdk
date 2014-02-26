package org.fenixedu.sdk.android;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.FenixEduClient;
import org.fenixedu.sdk.domain.CalendarFormat;

import com.google.gson.JsonObject;

/**
 * This endpoint returns the user's course information.
 */
public class GetPersonCoursesAsyncTask extends AuthorizedFenixEduAsyncTask<CalendarFormat, JsonObject> {

    public GetPersonCoursesAsyncTask(FenixEduClient client, Authorization authorization) {
        super(client, authorization);
    }

    @Override
    protected JsonObject doInBackground(CalendarFormat... params) {
        return getClient().getPersonCalendarClasses(getAuthorization(), params[0]);
    }
}
