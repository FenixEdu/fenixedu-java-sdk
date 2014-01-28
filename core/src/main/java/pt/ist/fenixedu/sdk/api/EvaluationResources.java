package pt.ist.fenixedu.sdk.api;

import pt.ist.fenixedu.sdk.EnrolAction;
import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

import com.google.gson.JsonArray;

public interface EvaluationResources {

    JsonArray getPersonEvaluations() throws FenixEduClientException;

    JsonArray enrollPersonInEvaluation(String evaluationId, EnrolAction action) throws FenixEduClientException;

}
