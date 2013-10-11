package pt.ist.fenixedu.sdk;

public enum Semester {

	FIRST("1"), SECOND("2");

	private final String semester;

	private Semester(String semester) {
		this.semester = semester;
	}

	@Override
	public String toString() {
		return this.semester;
	}

}
