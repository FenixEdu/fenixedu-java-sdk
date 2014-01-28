package org.fenixedu.sdk;

import java.io.IOException;

import org.fenixedu.commons.configuration.ConfigurationInvocationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixedu.sdk.auth.OAuthAuthorizationImpl;
import pt.ist.fenixedu.sdk.config.FenixEduClientConfigurationManager.Configuration;
import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

public class AsyncFenixEduClientFactory implements java.io.Serializable {

    private static final long serialVersionUID = 7312020100927208079L;

    private static final Logger logger = LoggerFactory.getLogger(AsyncFenixEduClientFactory.class);

    private static AsyncFenixEduClient INSTANCE;

    public static AsyncFenixEduClient getSingleton() throws FenixEduClientException {
        if (INSTANCE == null) {
            try {
                logger.debug("No instance of FenixEduClient found. Creating a new one...");
                Configuration config = ConfigurationInvocationHandler.getConfiguration(Configuration.class);
                INSTANCE = new AsyncFenixEduClientImpl(config, new OAuthAuthorizationImpl(config));
            } catch (IOException e) {
                throw new FenixEduClientException("Could not read the fenixedu.properties file.", e);
            }
        }
        return INSTANCE;
    }

}
