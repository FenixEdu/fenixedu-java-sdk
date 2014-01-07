package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.beans.FenixPersonCourses;
import pt.ist.fenixedu.sdk.beans.publico.FenixCourseEvaluation;

public class CourseEvaluationTest extends PersonCoursesTest {

    public CourseEvaluationTest() {
        super();
    }

    public CourseEvaluationTest(String name) {
        super(name);
    }

    @Override
    public void testNotNull() {
        super.testNotNull();
        FenixPersonCourses courses = getCourses();

        String courseId = courses.getEnrolments().get(0).getId();
        assertTrue("Enrolments must be greater than 0", courses.getEnrolments().size() > 0);

        FenixCourseEvaluation[] evaluations = getClient().getCourseEvaluations(courseId);

        assertNotNull("Evaluation is null", evaluations);

        for (FenixCourseEvaluation evaluation : evaluations) {
            assertNotNull("Name is null", evaluation.getName());
        }

    }

}
