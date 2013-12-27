package pt.ist.fenixedu.sdk.models;

/**
 * The Class Building.
 */
public class Building extends Campus {
	
	/** The parent space. */
	private Space parentSpace;
	
	/**
	 * Instantiates a new building.
	 */
	public Building() {
	}

	/**
	 * Gets the parent space.
	 *
	 * @return the parent space
	 */
	public Space getParentSpace() {
		return parentSpace;
	}

	/**
	 * Sets the parent space.
	 *
	 * @param parentSpace the new parent space
	 */
	public void setParentSpace(Space parentSpace) {
		this.parentSpace = parentSpace;
	}
	
	

}
