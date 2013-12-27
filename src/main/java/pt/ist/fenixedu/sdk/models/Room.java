package pt.ist.fenixedu.sdk.models;

public class Room extends Space {
	
	private Space parentSpace;
	private String description;
	private int normalCapacity;
	private int examCapacity;
	private Event[] events;
	
	public Room() {
	}

	public Space getParentSpace() {
		return parentSpace;
	}

	public void setParentSpace(Space parentSpace) {
		this.parentSpace = parentSpace;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNormalCapacity() {
		return normalCapacity;
	}

	public void setNormalCapacity(int normalCapacity) {
		this.normalCapacity = normalCapacity;
	}

	public int getExamCapacity() {
		return examCapacity;
	}

	public void setExamCapacity(int examCapacity) {
		this.examCapacity = examCapacity;
	}

	public Event[] getEvents() {
		return events;
	}

	public void setEvents(Event[] events) {
		this.events = events;
	}
	
	

}
