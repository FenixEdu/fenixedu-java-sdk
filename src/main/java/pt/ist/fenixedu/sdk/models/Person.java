package pt.ist.fenixedu.sdk.models;

/**
 * The Class Person.
 */
public class Person {
	
	/** The campus. */
	private String campus;
	
	/** The roles. */
	private Role[] roles;
	
	/** The photo. */
	private Photo photo;
	
	/** The name. */
	private String name;
	
	/** The ist id. */
	private String istId;
	
	/** The email. */
	private String email;
	
	/** The personal emails. */
	private String[] personalEmails;
	
	/** The work emails. */
	private String[] workEmails;
	
	/** The web addresses. */
	private String[] webAddresses;
	
	/** The work web addresses. */
	private String[] workWebAddresses;
	
	/**
	 * Instantiates a new person.
	 */
	public Person() {
	}

	/**
	 * Gets the campus.
	 *
	 * @return the campus
	 */
	public String getCampus() {
		return campus;
	}

	/**
	 * Sets the campus.
	 *
	 * @param campus the new campus
	 */
	public void setCampus(String campus) {
		this.campus = campus;
	}

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public Role[] getRoles() {
		return roles;
	}

	/**
	 * Sets the roles.
	 *
	 * @param roles the new roles
	 */
	public void setRoles(Role[] roles) {
		this.roles = roles;
	}

	/**
	 * Gets the photo.
	 *
	 * @return the photo
	 */
	public Photo getPhoto() {
		return photo;
	}

	/**
	 * Sets the photo.
	 *
	 * @param photo the new photo
	 */
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the ist id.
	 *
	 * @return the ist id
	 */
	public String getIstId() {
		return istId;
	}

	/**
	 * Sets the ist id.
	 *
	 * @param istId the new ist id
	 */
	public void setIstId(String istId) {
		this.istId = istId;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the personal emails.
	 *
	 * @return the personal emails
	 */
	public String[] getPersonalEmails() {
		return personalEmails;
	}

	/**
	 * Sets the personal emails.
	 *
	 * @param personalEmails the new personal emails
	 */
	public void setPersonalEmails(String[] personalEmails) {
		this.personalEmails = personalEmails;
	}

	/**
	 * Gets the work emails.
	 *
	 * @return the work emails
	 */
	public String[] getWorkEmails() {
		return workEmails;
	}

	/**
	 * Sets the work emails.
	 *
	 * @param workEmails the new work emails
	 */
	public void setWorkEmails(String[] workEmails) {
		this.workEmails = workEmails;
	}

	/**
	 * Gets the web addresses.
	 *
	 * @return the web addresses
	 */
	public String[] getWebAddresses() {
		return webAddresses;
	}

	/**
	 * Sets the web addresses.
	 *
	 * @param webAddresses the new web addresses
	 */
	public void setWebAddresses(String[] webAddresses) {
		this.webAddresses = webAddresses;
	}

	/**
	 * Gets the work web addresses.
	 *
	 * @return the work web addresses
	 */
	public String[] getWorkWebAddresses() {
		return workWebAddresses;
	}

	/**
	 * Sets the work web addresses.
	 *
	 * @param workWebAddresses the new work web addresses
	 */
	public void setWorkWebAddresses(String[] workWebAddresses) {
		this.workWebAddresses = workWebAddresses;
	}
	
	

}
