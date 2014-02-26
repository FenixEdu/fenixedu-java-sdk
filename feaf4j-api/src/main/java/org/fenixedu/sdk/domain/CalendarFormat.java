package org.fenixedu.sdk.domain;

public enum CalendarFormat {

	CALENDAR("calendar"), JSON("json");

	private String format;

	private CalendarFormat(String format) {
		this.format = format;
	}

	@Override
	public String toString() {
		return this.format;
	}

}
