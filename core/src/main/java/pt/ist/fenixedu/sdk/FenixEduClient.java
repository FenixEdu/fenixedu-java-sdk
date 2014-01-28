package pt.ist.fenixedu.sdk;

import pt.ist.fenixedu.sdk.api.CurricularResources;
import pt.ist.fenixedu.sdk.api.EvaluationResources;
import pt.ist.fenixedu.sdk.api.PaymentResources;
import pt.ist.fenixedu.sdk.api.PersonalResources;
import pt.ist.fenixedu.sdk.api.PublicResources;
import pt.ist.fenixedu.sdk.api.ScheduleResources;
import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

public interface FenixEduClient extends PublicResources, PersonalResources, ScheduleResources, CurricularResources,
        EvaluationResources, PaymentResources {

    PublicResources publicScope();

    PersonalResources personalScope();

    ScheduleResources scheduleScope();

    CurricularResources curricularScope();

    EvaluationResources evaluationScope();

    PaymentResources paymentScope();

    public String getAuthenticationUrl();

    public void setCode(String code) throws FenixEduClientException;

}
