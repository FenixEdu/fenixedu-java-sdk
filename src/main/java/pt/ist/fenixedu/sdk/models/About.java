package pt.ist.fenixedu.sdk.models;

/**
 * The Class About.
 */
public class About {
	
	/** The insitution name. */
	private String institutionName;
	
	/** The instution url. */
	private String institutionUrl;
	
	/** The rss feeds. */
	private RSSFeed[] rssFeeds;
	
	/**
	 * Instantiates a new about.
	 */
	public About() {
	}

	/**
	 * Gets the insitution name.
	 *
	 * @return the insitution name
	 */
	public String getInstitutionName() {
		return institutionName;
	}

	/**
	 * Sets the insitution name.
	 *
	 * @param insitutionName the new insitution name
	 */
	public void setInstitutionName(String insitutionName) {
		this.institutionName = insitutionName;
	}

	/**
	 * Gets the instution url.
	 *
	 * @return the instution url
	 */
	public String getInstitutionUrl() {
		return institutionUrl;
	}

	/**
	 * Sets the instution url.
	 *
	 * @param instutionUrl the new instution url
	 */
	public void setInstitutionUrl(String instutionUrl) {
		this.institutionUrl = instutionUrl;
	}

	/**
	 * Gets the rss feeds.
	 *
	 * @return the rss feeds
	 */
	public RSSFeed[] getRssFeeds() {
		return rssFeeds;
	}

	/**
	 * Sets the rss feeds.
	 *
	 * @param rssFeeds the new rss feeds
	 */
	public void setRssFeeds(RSSFeed[] rssFeeds) {
		this.rssFeeds = rssFeeds;
	}
	
	
	
}
