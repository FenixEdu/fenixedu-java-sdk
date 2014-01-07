package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.beans.FenixPersonCourses;
import pt.ist.fenixedu.sdk.beans.FenixPersonCourses.FenixEnrolment;

public class PersonCoursesTest extends FenixEduTestCase {
	private final int SEMESTER = 1;
	private final String YEAR = "2013/2014";
	private FenixPersonCourses courses;
	

	public PersonCoursesTest() {
	}

	public PersonCoursesTest(String name) {
		super(name);
	}

	public void testNotNull() {
		courses = getClient().getPersonCourses(SEMESTER, YEAR);
		
		assertNotNull("Courses list is null", courses);
		assertNotNull("Enrollments list is null", courses.getEnrolments());
		assertNotNull("Teaching list is null", courses.getTeaching());
		
		assertNotNull("Year is null", courses.getYear());
		assertEquals("Years must be the same", YEAR, courses.getYear());
		
		assertNotNull("Semester is null", courses.getSemester());
		assertEquals("Semesters must be the same", SEMESTER, courses.getSemester().intValue());
		
		for(FenixEnrolment enrolment : courses.getEnrolments()) {
			assertNotNull("Enrolment acronym is null", enrolment.getAcronym());
			//assertNotNull("Enrolment grade is null", enrolment.getGrade());
			assertNotNull("Enrolment id is null", enrolment.getId());
			assertNotNull("Enrolment name is null", enrolment.getName());
		}
		
		for(FenixPersonCourses.FenixCourse course : courses.getTeaching()) {
			assertNotNull("Teaching acronym is null", course.getAcronym());
			assertNotNull("Teaching id is null", course.getId());
			assertNotNull("Teaching name is null", course.getName());
		}
	}

	public FenixPersonCourses getCourses() {
		return courses;
	}

	public void setCourses(FenixPersonCourses courses) {
		this.courses = courses;
	}

	public int getSEMESTER() {
		return SEMESTER;
	}

	public String getYEAR() {
		return YEAR;
	}
	
	
}
