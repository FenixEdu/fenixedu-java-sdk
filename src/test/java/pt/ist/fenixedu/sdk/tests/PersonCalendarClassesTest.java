package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.CalendarFormat;
import pt.ist.fenixedu.sdk.beans.FenixCalendar;

public class PersonCalendarClassesTest extends PersonCalendarTest {

	public PersonCalendarClassesTest() {
	}

	public PersonCalendarClassesTest(String name) {
		super(name);
	}

	public void testNotNull() {
		FenixCalendar calendar = getClient().getPersonCalendarClasses(CalendarFormat.JSON);
		testNotNull(calendar);
	}
}
