package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.beans.FenixCurriculum;
import pt.ist.fenixedu.sdk.beans.FenixCurriculum.FenixCourseInfo;

public class PersonCurriculumTest extends FenixEduTestCase {

	public PersonCurriculumTest() {
	}

	public PersonCurriculumTest(String name) {
		super(name);
	}

	public void testNotNull() {
		FenixCurriculum[] curriculum = getClient().getPersonCurriculum();
		
		assertNotNull("Curriculum is null", curriculum);
		
		for(FenixCurriculum c : curriculum) {
			assertNotNull("Approved courses is null", c.getApprovedCourses());
			assertNotNull("Average is null", c.getAverage());
			assertNotNull("Campus is null", c.getCampus());
			assertNotNull("Course info is null", c.getCourseInfo());
			
			for(FenixCourseInfo info : c.getCourseInfo()) {
				assertNotNull("", info.getEcts());
				assertNotNull("", info.getGrade());
				assertNotNull("", info.getId());
				assertNotNull("", info.getName());
				assertNotNull("", info.getSemester());
				assertNotNull("", info.getYear());
			}
			
			assertNotNull("Degree type is null", c.getDegreeType());
			assertNotNull("ECTS is null", c.getEcts());
			//assertNotNull("End is null", c.getEnd());
			assertNotNull("Id is null", c.getId());
			assertNotNull("Is finished is null", c.getIsFinished());
			assertNotNull("Name is null", c.getName());
			assertNotNull("Presentation name is null", c.getPresentationName());
			assertNotNull("Start is null", c.getStart());
		}
	}
}
