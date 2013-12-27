package pt.ist.fenixedu.sdk;

import pt.ist.fenixedu.sdk.models.About;
import pt.ist.fenixedu.sdk.models.RSSFeed;
import pt.ist.fenixedu.sdk.models.Space;

public class TestMain {

	public TestMain() {
	}

	public static void main(String[] args) {
		String consumerKey = "7065221202521";
		String consumerSecret = "zHaiWfLxfW1Wt8rbwusDAx8lt24Z4PvG0tyzSS7607nxRZb23mTNBqVZzab9KGzU45RMH4z2tn8e4PJ7xDB8OSuTzBE0dBs8BN3vKatTb4rX1BNWcTq";
		String accessToken = null;
		String baseUrl = "https://fenix.ist.utl.pt";
		String callbackUrl = "http://localhost:8000/login";
		FenixEduConfig config = new FenixEduConfig(consumerKey, consumerSecret, accessToken, baseUrl, callbackUrl);
		FenixEduClient client = FenixEduClientFactory.getSingleton(config);
		
		client.setCode("6729fea2d356e1e09dd6369865ec6e6");
		
		Space[] spaces = client.getSpaces();
		
		for(Space space : spaces) {
			System.out.println(space.getName() + " " + space.getType() + " " + space.getId());
		}
		
	}

}
