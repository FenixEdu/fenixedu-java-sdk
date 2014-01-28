package org.fenixedu.sdk;

import java.io.Serializable;

import org.fenixedu.sdk.api.CurricularResourcesAsync;
import org.fenixedu.sdk.api.EvaluationResourcesAsync;
import org.fenixedu.sdk.api.PaymentResourcesAsync;
import org.fenixedu.sdk.api.PersonalResourcesAsync;
import org.fenixedu.sdk.api.PublicResourcesAsync;
import org.fenixedu.sdk.api.ScheduleResourcesAsync;

import pt.ist.fenixedu.sdk.FenixEduClientBase;
import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

public interface AsyncFenixEduClient extends Serializable, FenixEduClientBase, PublicResourcesAsync, PersonalResourcesAsync,
        EvaluationResourcesAsync, CurricularResourcesAsync, PaymentResourcesAsync, ScheduleResourcesAsync {

    PublicResourcesAsync publicScope();

    PersonalResourcesAsync personalScope();

    ScheduleResourcesAsync scheduleScope();

    CurricularResourcesAsync curricularScope();

    EvaluationResourcesAsync evaluationScope();

    PaymentResourcesAsync paymentScope();

    public String getAuthenticationUrl();

    public void setCode(String code) throws FenixEduClientException;

    void addListener(FenixEduClientListener listener);

}
