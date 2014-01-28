package pt.ist.fenixedu.sdk.api;

import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

import com.google.gson.JsonObject;

public interface PersonalResources {

    JsonObject getPerson() throws FenixEduClientException;

}
