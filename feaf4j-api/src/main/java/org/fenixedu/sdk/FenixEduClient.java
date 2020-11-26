package org.fenixedu.sdk;

import org.fenixedu.sdk.api.AssiduityResources;
import org.fenixedu.sdk.api.CurricularResources;
import org.fenixedu.sdk.api.DegreeCurricularManagementResources;
import org.fenixedu.sdk.api.EvaluationResources;
import org.fenixedu.sdk.api.PaymentResources;
import org.fenixedu.sdk.api.PersonalResources;
import org.fenixedu.sdk.api.PublicResources;
import org.fenixedu.sdk.api.ScheduleResources;
import org.fenixedu.sdk.exception.ApiClientException;

public interface FenixEduClient extends FenixEduClientBase, AssiduityResources, PublicResources, PersonalResources,
        ScheduleResources, CurricularResources, EvaluationResources, PaymentResources, DegreeCurricularManagementResources {

    PublicResources publicScope();

    PersonalResources personalScope();

    ScheduleResources scheduleScope();

    CurricularResources curricularScope();

    EvaluationResources evaluationScope();

    PaymentResources paymentScope();

    AssiduityResources assiduityScope();

    DegreeCurricularManagementResources degreeCurricularManagementScope();

    public String getAuthenticationUrl();

    public OAuthAuthorization refreshAccessToken(OAuthAuthorization oldAuthorization);

    public FenixEduUserDetails getUserDetailsFromCode(String code) throws ApiClientException;

}
