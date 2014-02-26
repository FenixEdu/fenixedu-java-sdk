package org.fenixedu.sdk.android;

import org.fenixedu.sdk.FenixEduClient;

import com.google.gson.JsonObject;

/**
 * A course is a concrete unit of teaching that typically lasts one academic term. This endpoint shows some information regarding
 * a particular course. The same course may be lectured simultaneously in multiple degrees during the same academic term.
 * 
 * The "competences" field holds curricular information for each set of degrees in which the course is lectured. Usually this
 * information is the same for all the associated degrees.
 */
public class GetCourseAsyncTask extends FenixEduAsyncTask<String, JsonObject> {

    public GetCourseAsyncTask(FenixEduClient client) {
        super(client);
    }

    @Override
    protected JsonObject doInBackground(String... params) {
        return getClient().publicScope().getCourse(params[0]);
    }

}
