package org.fenixedu.sdk.api;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.domain.EnrolAction;

import com.google.gson.JsonArray;

public interface EvaluationResources {

    JsonArray getPersonEvaluations(Authorization authorization);

    JsonArray enrollPersonInEvaluation(Authorization authorization, String evaluationId, EnrolAction action);

}
