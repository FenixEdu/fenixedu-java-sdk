package org.fenixedu.sdk.android;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.FenixEduClient;
import org.fenixedu.sdk.domain.EnrolAction;

import com.google.gson.JsonArray;

public class EnrollPersonInEvaluationAsyncTask extends AuthorizedFenixEduAsyncTask<EnrolAction, JsonArray> {

    private final String evaluationId;

    /**
     * A task that enrolls the student with the given authorization in an evaluation identified by the provided id.
     *
     * @param client the fenixedu client singleton instance.
     * @param authorization the authorization identifying the student to be enrolled.
     * @param evaluationId the evaluation id to enroll the student in.
     */
    public EnrollPersonInEvaluationAsyncTask(FenixEduClient client, Authorization authorization, String evaluationId) {
        super(client, authorization);
        this.evaluationId = evaluationId;
    }

    public EnrollPersonInEvaluationAsyncTask(FenixEduClient client, PostExecuteCallback postExecuteCallback,
            Authorization authorization, String evaluationId) {
        super(client, postExecuteCallback, authorization);
        this.evaluationId = evaluationId;
    }

    /**
     * Enrolls a student in an evaluation.
     *
     * @param params should receive a single EnrolAction
     */
    @Override
    protected JsonArray executeInBackground(EnrolAction... params) {
        return getClient().enrollPersonInEvaluation(getAuthorization(), evaluationId, params[0]);
    }
}
