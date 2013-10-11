package pt.ist.fenixedu.sdk;

import java.io.IOException;

public class FenixEduClientFactory {

	private static FenixEduClient INSTANCE;

	public static FenixEduClient getSingleton() {
		if (INSTANCE == null) {
			try {
				INSTANCE = new FenixEduClient();
			} catch (IOException e) {
				throw new FenixEduException(
						"Could not read the fenixedu.config file.", e);
			}
		}
		return INSTANCE;
	}

}
