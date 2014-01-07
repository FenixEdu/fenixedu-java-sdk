package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.FenixEduClient;
import pt.ist.fenixedu.sdk.FenixEduClientFactory;
import pt.ist.fenixedu.sdk.FenixEduConfig;
import pt.ist.fenixedu.sdk.beans.publico.FenixAbout;
import pt.ist.fenixedu.sdk.beans.publico.FenixAbout.FenixRSSFeed;
import junit.framework.TestCase;

public class AboutTest extends TestCase {
	
	private String consumerKey = "7065221202521";
	private String consumerSecret = "zHaiWfLxfW1Wt8rbwusDAx8lt24Z4PvG0tyzSS7607nxRZb23mTNBqVZzab9KGzU45RMH4z2tn8e4PJ7xDB8OSuTzBE0dBs8BN3vKatTb4rX1BNWcTq";
	private String accessToken = "";
	private String baseUrl = "https://fenix.ist.utl.pt";
	private String callbackUrl = "http://localhost:8000/login";
	
	private FenixEduClient client;

	public AboutTest() {
	}

	public AboutTest(String name) {
		super(name);
	}
	
	public void setUp() {
		FenixEduConfig config = new FenixEduConfig(consumerKey, consumerSecret, accessToken, baseUrl, callbackUrl);
		try {
			client  = FenixEduClientFactory.getSingleton();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void testNotNull() {
		FenixAbout about = client.getAbout();
		
		assertNotNull("Institution name null", about.getInstitutionName());
		assertNotNull("Institution url is null", about.getInstitutionUrl());
		
		assertNotNull("RSS feeds are null", about.getRssFeeds());
		
		for(FenixRSSFeed feed : about.getRssFeeds()) {
			assertNotNull("Feed description null", feed.getDescription());
			assertNotNull("Feed uri is null", feed.getUri());
		}
		
	}
}
