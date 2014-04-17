package org.fenixedu.sdk.android;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.FenixEduClient;
import org.fenixedu.sdk.domain.CalendarFormat;

import com.google.gson.JsonObject;

/**
 * This endpoint returns the user's class information.
 * This information can be retrieved both in iCalendar and JSON formats.
 */
public class GetPersonCalendarClassesAsyncTask extends AuthorizedFenixEduAsyncTask<CalendarFormat, JsonObject> {

    public GetPersonCalendarClassesAsyncTask(FenixEduClient client, Authorization authorization) {
        super(client, authorization);
    }

    @Override
    protected JsonObject doInBackground(CalendarFormat... params) {
        return getClient().getPersonCalendarClasses(getAuthorization(), params[0]);
    }
}
