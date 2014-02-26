package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonObject;

/**
 * This endpoint lists all the students attending the specified course.
 * For each student it indicates the corresponding degree.
 * The endpoint also returns the number of students officially enroled in the course.
 */
public class GetCourseStudentsAsyncTask extends FenixEduAsyncTask<String, JsonObject> {

    public GetCourseStudentsAsyncTask(FenixEduClient client) {
        super(client);
    }

    @Override
    protected JsonObject doInBackground(String... params) {
        return getClient().publicScope().getCourseSchedule(params[0]);
    }

}
