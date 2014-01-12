package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.beans.FenixPersonCourses;
import pt.ist.fenixedu.sdk.beans.publico.FenixCourse;
import pt.ist.fenixedu.sdk.beans.publico.FenixCourse.FenixCompetence;
import pt.ist.fenixedu.sdk.beans.publico.FenixCourse.FenixCompetence.BiblioRef;
import pt.ist.fenixedu.sdk.beans.publico.FenixCourse.FenixCompetence.Degree;

public class CourseTest extends PersonCoursesTest {

	public CourseTest() {
	}

	public CourseTest(String name) {
		super(name);
	}

	public void notNull() {
		super.testNotNull();
		
		FenixPersonCourses courses = getCourses();
		String courseId = courses.getEnrolments().get(0).getId();
		
		FenixCourse course = getClient().getCourse(courseId);
		
		assertNotNull("Course is null", course);
		
		assertNotNull("Academic term is null", course.getAcademicTerm());
		assertNotNull("Acronym is null", course.getAcronym());
		assertNotNull("Announcement link is null", course.getAnnouncementLink());
		assertNotNull("Evaluation method is null", course.getEvaluationMethod());
		assertNotNull("More info is null", course.getMoreInfo());
		assertNotNull("Name is null", course.getName());
		assertNotNull("Number of attending students is null", course.getNumberOfAttendingStudents());
		assertNotNull("Summary lin is null", course.getSummaryLink());
		assertNotNull("Teachers list is null", course.getTeachers());
		
		for(FenixCompetence competence : course.getMoreInfo()) {
			assertNotNull("Bibliographic references list is null", competence.getBibliographicReferences());
			assertNotNull("Degrees list is null", competence.getDegrees());
			assertNotNull("Program is null", competence.getProgram());
			
			for(BiblioRef ref : competence.getBibliographicReferences()) {
				assertNotNull("Biblio ref author is null", ref.getAuthor());
				assertNotNull("Biblio ref reference is null", ref.getReference());
				assertNotNull("Biblio ref title is null", ref.getTitle());
				assertNotNull("Biblio ref type is null", ref.getType());
				assertNotNull("Biblio ref url is null", ref.getUrl());
				assertNotNull("Biblio ref year is null", ref.getYear());
			}
			for(Degree degree : competence.getDegrees()) {
				assertNotNull("Degree acronym is null", degree.getAcronym());
				assertNotNull("Degree id is null", degree.getId());
				assertNotNull("Degree name is null", degree.getName());
			}
		}
	}
	
}
