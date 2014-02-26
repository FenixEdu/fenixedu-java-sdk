package org.fenixedu.sdk.impl;

import org.fenixedu.sdk.HttpClientFactoryBinder;
import org.fenixedu.sdk.IHttpClientFactory;

public class StaticHttpClientBinder implements HttpClientFactoryBinder {

    private static final StaticHttpClientBinder SINGLETON = new StaticHttpClientBinder();

    public static final StaticHttpClientBinder getSingleton() {
        return SINGLETON;
    }

    private StaticHttpClientBinder() {
        throw new UnsupportedOperationException("This code should have never made it into fef4j-api.jar");
    }

    @Override
    public IHttpClientFactory getHttpClientFactory() {
        throw new UnsupportedOperationException("This code should never make it into fef4j-api.jar");
    }

    @Override
    public String getHttpClientFactoryClassStr() {
        throw new UnsupportedOperationException("This code should never make it into fef4j-api.jar");
    }

}
