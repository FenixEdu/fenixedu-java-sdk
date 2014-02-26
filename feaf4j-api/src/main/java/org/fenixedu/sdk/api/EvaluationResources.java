package org.fenixedu.sdk.api;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.domain.EnrolAction;
import org.fenixedu.sdk.exception.FenixEduClientException;

import com.google.gson.JsonArray;

public interface EvaluationResources {

    JsonArray getPersonEvaluations(Authorization authorization) throws FenixEduClientException;

    JsonArray enrollPersonInEvaluation(Authorization authorization, String evaluationId, EnrolAction action)
            throws FenixEduClientException;

}
