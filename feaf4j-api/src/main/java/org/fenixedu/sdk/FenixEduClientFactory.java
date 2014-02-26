package org.fenixedu.sdk;

public class FenixEduClientFactory {

    private static FenixEduClientImpl SINGLETON = null;

    public static FenixEduClient getAndDefineSingletonFromApplicationConfiguration(ApplicationConfiguration config) {
        if (SINGLETON == null) {
            SINGLETON = new FenixEduClientImpl(config);
        }
        return SINGLETON;
    }

    public static FenixEduClient getSingleton() {
        if (SINGLETON == null) {
            SINGLETON = new FenixEduClientImpl(ApplicationConfiguration.fromProperties("fenixedu.properties"));
        }
        return SINGLETON;
    }

    public static FenixEduClient fromConfig(ApplicationConfiguration config) {
        return new FenixEduClientImpl(config);
    }

}
