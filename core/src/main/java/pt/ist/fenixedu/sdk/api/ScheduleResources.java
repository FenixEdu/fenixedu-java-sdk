package pt.ist.fenixedu.sdk.api;

import pt.ist.fenixedu.sdk.CalendarFormat;
import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

import com.google.gson.JsonObject;

public interface ScheduleResources {

    JsonObject getPersonCalendarClasses(CalendarFormat format) throws FenixEduClientException;

    JsonObject getPersonCalendarEvaluations(CalendarFormat format) throws FenixEduClientException;

}
