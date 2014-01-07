package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.CalendarFormat;
import pt.ist.fenixedu.sdk.beans.FenixCalendar;

public class PersonCalendarEvaluationsTest extends PersonCalendarTest {

	public PersonCalendarEvaluationsTest() {
	}

	public PersonCalendarEvaluationsTest(String name) {
		super(name);
	}

	public void testNotNull() {
		FenixCalendar calendar = getClient().getPersonCalendarEvaluations(CalendarFormat.JSON);
		testNotNull(calendar);
	}
}
