package pt.ist.fenixedu.sdk.beans;

import java.util.List;

public class FenixPersonCourses {

    public static class FenixCourse {
        private String id;
        private String acronym;
        private String name;
        private String academicTerm;
        private String url;

        public FenixCourse(String id, String acronym, String name) {
            super();
            this.id = id;
            this.acronym = acronym;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAcronym() {
            return acronym;
        }

        public void setAcronym(String acronym) {
            this.acronym = acronym;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

		public String getAcademicTerm() {
			return academicTerm;
		}

		public void setAcademicTerm(String academicTerm) {
			this.academicTerm = academicTerm;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
		
    }

    public static class FenixEnrolment extends FenixCourse {
        private String grade;

        public FenixEnrolment(String id, String acronym, String name, String grade) {
            super(id, acronym, name);
            setGrade(grade);
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

    }

    
    private List<FenixEnrolment> enrolments;
    private List<FenixCourse> teaching;

    public FenixPersonCourses() {

    }

    public FenixPersonCourses(String academicTerm, List<FenixEnrolment> enrolments, List<FenixCourse> teaching) {
        super();
        this.enrolments = enrolments;
        this.teaching = teaching;
    }

	public List<FenixEnrolment> getEnrolments() {
        return enrolments;
    }

    public void setEnrolments(List<FenixEnrolment> enrolments) {
        this.enrolments = enrolments;
    }

    public List<FenixCourse> getTeaching() {
        return teaching;
    }

    public void setTeaching(List<FenixCourse> teaching) {
        this.teaching = teaching;
    }

}
