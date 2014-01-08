package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.beans.publico.FenixCourseEvaluation;

public class CourseEvaluationsTest extends PersonCoursesTest {

	public CourseEvaluationsTest() {
	}

	public CourseEvaluationsTest(String name) {
		super(name);
	}

	public void testNotNull() {
		super.testNotNull();
		String courseId = getCourses().getEnrolments().get(0).getId();
		
		FenixCourseEvaluation[] evaluations = getClient().getCourseEvaluations(courseId);
		
		for(FenixCourseEvaluation evaluation : evaluations) {
			assertNotNull("Name is null", evaluation.getName());
		}
	}
}
