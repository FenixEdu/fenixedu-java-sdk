package org.fenixedu.sdk.api;

import org.fenixedu.sdk.Authorization;

import com.google.gson.JsonObject;

public interface AssiduityResources {

    JsonObject getAssiduity(Authorization authorization, String date);

}
