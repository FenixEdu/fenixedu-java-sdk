package org.fenixedu.sdk.api;

import org.fenixedu.sdk.Authorization;

import com.google.gson.JsonObject;

public interface PersonalResources {

    JsonObject getPerson(Authorization authorization);

}
