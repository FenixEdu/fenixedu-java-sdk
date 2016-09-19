package org.fenixedu.sdk.impl;

import org.fenixedu.sdk.HttpClient;
import org.fenixedu.sdk.IHttpClientFactory;

public class OkHttpClientFactory implements IHttpClientFactory {

    @Override
    public HttpClient getHttpClient() {
        return OkHttpClientMediator.getInstance();
    }
}
