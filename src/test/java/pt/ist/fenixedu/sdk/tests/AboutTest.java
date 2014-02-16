package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.beans.publico.FenixAbout;
import pt.ist.fenixedu.sdk.beans.publico.FenixAbout.FenixRSSFeed;

public class AboutTest extends FenixEduTestCase {
	
	public AboutTest() {
	}

	public AboutTest(String name) {
		super(name);
	}
	
	public void testNotNull() {
		FenixAbout about = getClient().getAbout();
		
		assertNotNull("Institution name null", about.getInstitutionName());
		assertNotNull("Institution url is null", about.getInstitutionUrl());
		
		assertNotNull("RSS feeds are null", about.getRssFeeds());
		
		for(FenixRSSFeed feed : about.getRssFeeds()) {
			assertNotNull("Feed description null", feed.getDescription());
			assertNotNull("Feed uri is null", feed.getUrl());
		}
		
	}
}
