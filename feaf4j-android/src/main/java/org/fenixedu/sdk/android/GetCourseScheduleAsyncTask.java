package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonArray;

/**
 * Each course is lectured during a specific set of intervals.
 * These intervals make up the lesson period for that course.
 * Each course also has a curricular load that specifies the time each student will expend with the course.
 * Each shift is the possible schedule in which a student should enrol.
 */
public class GetCourseScheduleAsyncTask extends FenixEduAsyncTask<String, JsonArray> {

    public GetCourseScheduleAsyncTask(FenixEduClient client) {
        super(client);
    }

    public GetCourseScheduleAsyncTask(FenixEduClient client, PostExecuteCallback postExecuteCallback) {
        super(client, postExecuteCallback);
    }

    @Override
    protected JsonArray executeInBackground(String... params) {
        return getClient().publicScope().getCourseGroups(params[0]);
    }

}
