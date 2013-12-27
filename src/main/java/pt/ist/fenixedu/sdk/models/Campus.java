package pt.ist.fenixedu.sdk.models;

/**
 * The Class Campus.
 */
public class Campus extends Space {
	
	/** The contained spaces. */
	private Space[] containedSpaces;

	/**
	 * Instantiates a new campus.
	 */
	public Campus() {
	}

	/**
	 * Gets the contained spaces.
	 *
	 * @return the contained spaces
	 */
	public Space[] getContainedSpaces() {
		return containedSpaces;
	}

	/**
	 * Sets the contained spaces.
	 *
	 * @param containedSpaces the new contained spaces
	 */
	public void setContainedSpaces(Space[] containedSpaces) {
		this.containedSpaces = containedSpaces;
	}

	
}
