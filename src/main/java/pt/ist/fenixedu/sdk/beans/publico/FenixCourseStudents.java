package pt.ist.fenixedu.sdk.beans.publico;

import java.util.ArrayList;
import java.util.List;

public class FenixCourseStudents {

    public static class FenixCourseStudent {

        private String username;
        private String degree;
        private String degreeId;

        public FenixCourseStudent() {
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getDegreeId() {
            return degreeId;
        }

        public void setDegreeId(String degreeId) {
            this.degreeId = degreeId;
        }

    }

    private int enrolmentCount;
    private int attendingCount;
    private List<FenixCourseStudent> students = new ArrayList<FenixCourseStudent>();

    public FenixCourseStudents() {
    }

    public int getEnrolmentCount() {
        return enrolmentCount;
    }

    public void setEnrolmentCount(int enrolmentCount) {
        this.enrolmentCount = enrolmentCount;
    }

    public int getAttendingCount() {
        return attendingCount;
    }

    public void setAttendingCount(int attendingCount) {
        this.attendingCount = attendingCount;
    }

    public List<FenixCourseStudent> getStudents() {
        return students;
    }

    public void setStudents(List<FenixCourseStudent> students) {
        this.students = students;
    }

}
