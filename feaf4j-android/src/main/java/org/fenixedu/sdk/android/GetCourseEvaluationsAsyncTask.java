package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonArray;

/**
 * An evaluation is a component of a course in which the teacher determines the extent of the students understanding of the
 * program.
 * Current known implementations of evaluations are: tests, exams, projects, online tests and ad-hoc evaluations.
 */
public class GetCourseEvaluationsAsyncTask extends FenixEduAsyncTask<String, JsonArray> {

    public GetCourseEvaluationsAsyncTask(FenixEduClient client) {
        super(client);
    }

    public GetCourseEvaluationsAsyncTask(FenixEduClient client, PostExecuteCallback postExecuteCallback) {
        super(client, postExecuteCallback);
    }

    @Override
    protected JsonArray executeInBackground(String... params) {
        return getClient().publicScope().getCourseEvaluations(params[0]);
    }

}
