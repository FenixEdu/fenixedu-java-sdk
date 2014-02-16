package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.beans.FenixPerson;
import pt.ist.fenixedu.sdk.beans.FenixPerson.FenixRole;

public class PersonTest extends FenixEduTestCase {

	public PersonTest() {
	}

	public PersonTest(String name) {
		super(name);
	}
	
	public void testNotNull() {
		FenixPerson person = getClient().getPerson();
		
		assertNotNull("Campus is null", person.getCampus());
		assertNotNull("Email is null", person.getEmail());
		assertNotNull("username is null", person.getUsername());
		assertNotNull("name is null", person.getName());
		assertNotNull("personal emails are null", person.getPersonalEmails());
		
		for(String email : person.getPersonalEmails()) {
			assertNotNull("", email);
		}
		
		//assertNotNull("Photo is null", person.getPhoto());
		if(person.getPhoto() != null) {
			assertNotNull("Photo type is null", person.getPhoto().getType());
			assertNotNull("Photo data is null", person.getPhoto().getData());	
		}
		
		assertNotNull("Rules are null", person.getRoles());
		
		for(FenixRole role : person.getRoles()) {
			assertNotNull("Role is null", role);
		}
		
		assertNotNull("Web addresses are null", person.getWebAddresses());
		
		assertNotNull("Work emails are null", person.getWorkEmails());
		
		assertNotNull("Work web addresses are null", person.getWorkWebAddresses());		
	}
}
