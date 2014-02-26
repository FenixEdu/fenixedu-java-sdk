package org.fenixedu.sdk.api;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.exception.FenixEduClientException;

import com.google.gson.JsonObject;

public interface PaymentResources {

    JsonObject getPersonPayments(Authorization authorization) throws FenixEduClientException;

}
