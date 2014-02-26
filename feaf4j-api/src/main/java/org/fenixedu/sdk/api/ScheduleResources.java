package org.fenixedu.sdk.api;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.domain.CalendarFormat;
import org.fenixedu.sdk.exception.FenixEduClientException;

import com.google.gson.JsonObject;

public interface ScheduleResources {

    JsonObject getPersonCalendarClasses(Authorization authorization, CalendarFormat format) throws FenixEduClientException;

    JsonObject getPersonCalendarEvaluations(Authorization authorization, CalendarFormat format) throws FenixEduClientException;

}
