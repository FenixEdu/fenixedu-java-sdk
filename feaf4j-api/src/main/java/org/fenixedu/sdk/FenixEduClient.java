package org.fenixedu.sdk;

import org.fenixedu.sdk.api.CurricularResources;
import org.fenixedu.sdk.api.EvaluationResources;
import org.fenixedu.sdk.api.PaymentResources;
import org.fenixedu.sdk.api.PersonalResources;
import org.fenixedu.sdk.api.PublicResources;
import org.fenixedu.sdk.api.ScheduleResources;
import org.fenixedu.sdk.exception.FenixEduClientException;

public interface FenixEduClient extends FenixEduClientBase, PublicResources, PersonalResources, ScheduleResources,
        CurricularResources, EvaluationResources, PaymentResources {

    PublicResources publicScope();

    PersonalResources personalScope();

    ScheduleResources scheduleScope();

    CurricularResources curricularScope();

    EvaluationResources evaluationScope();

    PaymentResources paymentScope();

    public String getAuthenticationUrl();

    public Authorization refreshAccessToken(Authorization oldAuthorization);

    public FenixEduUserDetails getUserDetailsFromCode(String code) throws FenixEduClientException;

}
