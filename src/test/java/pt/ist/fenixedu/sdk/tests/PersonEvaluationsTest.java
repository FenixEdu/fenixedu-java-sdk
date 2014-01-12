package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.beans.FenixEvaluation;

public class PersonEvaluationsTest extends FenixEduTestCase {

	public PersonEvaluationsTest() {
	}

	public PersonEvaluationsTest(String name) {
		super(name);
	}

	public void testNotNull() {
		FenixEvaluation[] evaluations = getClient().getPersonEvaluations();
		
		assertNotNull("Evaluations list is null", evaluations);
		
		for(FenixEvaluation e : evaluations) {
			assertNotNull("Course is null", e.getCourse());
			assertNotNull("Day is null", e.getDay());
			assertNotNull("End hour is null", e.getEndHour());
			assertNotNull("Enrollment begin day is null ", e.getEnrollmentBeginDay());
			assertNotNull("Enrollment end day is null", e.getEnrollmentEndDay());
			assertNotNull("Id is null", e.getId());
			assertNotNull("Is enrolled is null", e.getIsEnrolled());
			assertNotNull("Is enrollment period is null", e.getIsEnrollmentPeriod());
			assertNotNull("Name is null", e.getName());
			assertNotNull("Rooms is null", e.getRooms());
			assertNotNull("Start hour is null", e.getStartHour());
			assertNotNull("Type is null", e.getType());
		}
	}
}
