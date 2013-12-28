package pt.ist.fenixedu.sdk.models;

/**
 * The Class TeacherRole.
 */
public class TeacherRole extends Role {
	
	/** The department. */
	private String department;
	
	/**
	 * Instantiates a new teacher.
	 */
	public TeacherRole() {
	}

	/**
	 * Gets the department.
	 *
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Sets the department.
	 *
	 * @param department the new department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	

}
