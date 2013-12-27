package pt.ist.fenixedu.sdk.models;

/**
 * The Class RSSFeed.
 */
public class RSSFeed {
	
	/** The description. */
	private String description;
	
	/** The uri. */
	private String uri;

	/**
	 * Instantiates a new rSS feed.
	 */
	public RSSFeed() {
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the uri.
	 *
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Sets the uri.
	 *
	 * @param uri the new uri
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

}
