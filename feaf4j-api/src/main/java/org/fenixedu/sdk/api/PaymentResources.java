package org.fenixedu.sdk.api;

import org.fenixedu.sdk.Authorization;

import com.google.gson.JsonObject;

public interface PaymentResources {

    JsonObject getPersonPayments(Authorization authorization);

}
