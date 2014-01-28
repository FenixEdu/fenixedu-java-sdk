package org.fenixedu.sdk.api;

import pt.ist.fenixedu.sdk.CalendarFormat;
import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

public interface ScheduleResourcesAsync {

    void getPersonCalendarClasses(CalendarFormat format) throws FenixEduClientException;

    void getPersonCalendarEvaluations(CalendarFormat format) throws FenixEduClientException;

}
