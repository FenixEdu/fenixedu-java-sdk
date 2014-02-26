package org.fenixedu.sdk;


public class FenixEduClientFactory {

    private static FenixEduClientImpl SINGLETON = null;

    public static FenixEduClient getSingleton(ApplicationConfiguration config) {
        if (SINGLETON == null) {
            SINGLETON = new FenixEduClientImpl(config);
        }
        return SINGLETON;
    }
}
