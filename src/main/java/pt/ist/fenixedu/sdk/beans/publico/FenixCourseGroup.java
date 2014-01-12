package pt.ist.fenixedu.sdk.beans.publico;

public class FenixCourseGroup {

    public static class GroupedCourse {

        private String name;
        private String degrees;
        private String id;

        public GroupedCourse() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDegrees() {
            return degrees;
        }

        public void setDegrees(String degrees) {
            this.degrees = degrees;
        }

    }

    private String name;
    private String description;
    private FenixInterval enrolmentPeriod;
    private String enrolmentPolicy;
    private Integer minimumCapacity;
    private Integer maximumCapacity;
    private Integer idealCapacity;
    private GroupedCourse[] associatedCourses;

    public FenixCourseGroup() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public FenixInterval getEnrolmentPeriod() {
        return enrolmentPeriod;
    }

    public void setEnrolmentPeriod(final FenixInterval enrolmentPeriod) {
        this.enrolmentPeriod = enrolmentPeriod;
    }

    public String getEnrolmentPolicy() {
        return enrolmentPolicy;
    }

    public void setEnrolmentPolicy(final String enrolmentPolicy) {
        this.enrolmentPolicy = enrolmentPolicy;
    }

    public Integer getMinimumCapacity() {
        return minimumCapacity;
    }

    public void setMinimumCapacity(final Integer minimumCapacity) {
        this.minimumCapacity = minimumCapacity;
    }

    public Integer getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(final Integer maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public Integer getIdealCapacity() {
        return idealCapacity;
    }

    public void setIdealCapacity(final Integer idealCapacity) {
        this.idealCapacity = idealCapacity;
    }

	public GroupedCourse[] getAssociatedCourses() {
		return associatedCourses;
	}

	public void setAssociatedCourses(GroupedCourse[] associatedCourses) {
		this.associatedCourses = associatedCourses;
	}
    
    

}
