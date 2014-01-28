package pt.ist.fenixedu.sdk.api;

import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

import com.google.gson.JsonObject;

public interface PaymentResources {

    JsonObject getPersonPayments() throws FenixEduClientException;

}
