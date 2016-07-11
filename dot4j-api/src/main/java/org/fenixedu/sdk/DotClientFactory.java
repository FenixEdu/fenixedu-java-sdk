package org.fenixedu.sdk;

public class DotClientFactory {

    private static DotClientImpl SINGLETON = null;

    public static DotClient getAndDefineSingletonFromApplicationConfiguration(ApplicationConfiguration config) {
        if (SINGLETON == null) {
            SINGLETON = new DotClientImpl(config);
        }
        return SINGLETON;
    }

    public static DotClient getSingleton() {
        if (SINGLETON == null) {
            SINGLETON = new DotClientImpl(ApplicationConfiguration.fromPropertyFilename("fenixedu.properties"));
        }
        return SINGLETON;
    }

    public static DotClient fromConfig(ApplicationConfiguration config) {
        return new DotClientImpl(config);
    }

}
