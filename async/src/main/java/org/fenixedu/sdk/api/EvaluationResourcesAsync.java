package org.fenixedu.sdk.api;

import pt.ist.fenixedu.sdk.EnrolAction;
import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

public interface EvaluationResourcesAsync {

    void getPersonEvaluations() throws FenixEduClientException;

    void enrollPersonInEvaluation(String evaluationId, EnrolAction action) throws FenixEduClientException;

}
