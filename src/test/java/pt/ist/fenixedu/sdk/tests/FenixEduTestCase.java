package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.FenixEduClient;
import pt.ist.fenixedu.sdk.FenixEduClientFactory;
import junit.framework.TestCase;

public abstract class FenixEduTestCase extends TestCase {
	
	private FenixEduClient client;

	public FenixEduTestCase() {
	}

	public FenixEduTestCase(String name) {
		super(name);
	}

	public FenixEduClient getClient() {
		return client;
	}

	public void setClient(FenixEduClient client) {
		this.client = client;
	}

	public void setUp() {
		client = FenixEduClientFactory.getSingleton();
	}
}
