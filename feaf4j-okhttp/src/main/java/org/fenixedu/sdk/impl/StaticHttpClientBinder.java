package org.fenixedu.sdk.impl;

import org.fenixedu.sdk.HttpClientFactoryBinder;
import org.fenixedu.sdk.IHttpClientFactory;
import org.fenixedu.sdk.impl.StaticHttpClientBinder;

public class StaticHttpClientBinder implements HttpClientFactoryBinder {

    private static final StaticHttpClientBinder SINGLETON = new StaticHttpClientBinder();

    public static final StaticHttpClientBinder getSingleton() {
        return SINGLETON;
    }

    private static final String httpClientFactoryClassStr = OkHttpClientFactory.class.getName();

    private final IHttpClientFactory httpClientFactory;

    private StaticHttpClientBinder() {
        this.httpClientFactory = new OkHttpClientFactory();
    }

    @Override
    public IHttpClientFactory getHttpClientFactory() {
        return this.httpClientFactory;
    }

    @Override
    public String getHttpClientFactoryClassStr() {
        return httpClientFactoryClassStr;
    }
}
