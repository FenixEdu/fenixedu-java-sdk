package pt.ist.fenixedu.sdk.tests;

import java.util.List;

import pt.ist.fenixedu.sdk.beans.publico.FenixCourseEvaluation;

public class CourseEvaluationsTest extends PersonCoursesTest {

    public CourseEvaluationsTest() {
    }

    public CourseEvaluationsTest(String name) {
        super(name);
    }

    @Override
    public void testNotNull() {
        super.testNotNull();
        String courseId = getCourses().getEnrolments().get(0).getId();

        List<FenixCourseEvaluation> evaluations = getClient().getCourseEvaluations(courseId);

        for (FenixCourseEvaluation evaluation : evaluations) {
            assertNotNull("Name is null", evaluation.getName());
        }
    }
}
