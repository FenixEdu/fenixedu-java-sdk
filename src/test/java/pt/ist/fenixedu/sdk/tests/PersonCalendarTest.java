package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.beans.FenixCalendar;
import pt.ist.fenixedu.sdk.beans.FenixCalendar.FenixCalendarEvent;

public abstract class PersonCalendarTest extends FenixEduTestCase {

	public PersonCalendarTest() {
	}

	public PersonCalendarTest(String name) {
		super(name);
	}
	
	public void testNotNull(FenixCalendar calendar) {
		
		assertNotNull("Year is null", calendar.getYear());
		assertNotNull("Events list is null", calendar.getEvents());
		
		for(FenixCalendarEvent event : calendar.getEvents()) {
			assertNotNull("End day is null", event.getEndDay());
			assertNotNull("End time is null", event.getEndTime());
			assertNotNull("Is all day is null", event.getIsAllDay());
			//assertNotNull("Location is null", event.getLocation());
			//assertNotNull("Note is null", event.getNote());
			assertNotNull("Start day is null", event.getStartDay());
			assertNotNull("Start time is null", event.getStartTime());
			assertNotNull("Title is null", event.getTitle());
			//assertNotNull("Url is null", event.getUrl());
		}
	}

}
