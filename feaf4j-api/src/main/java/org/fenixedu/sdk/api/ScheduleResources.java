package org.fenixedu.sdk.api;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.domain.CalendarFormat;

import com.google.gson.JsonObject;

public interface ScheduleResources {

    JsonObject getPersonCalendarClasses(Authorization authorization, CalendarFormat format);

    JsonObject getPersonCalendarEvaluations(Authorization authorization, CalendarFormat format);

}
