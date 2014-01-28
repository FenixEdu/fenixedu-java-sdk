package pt.ist.fenixedu.sdk.config;

import org.fenixedu.commons.configuration.ConfigurationInvocationHandler;
import org.fenixedu.commons.configuration.ConfigurationManager;
import org.fenixedu.commons.configuration.ConfigurationProperty;

public class FenixEduClientConfigurationManager {

    @ConfigurationManager(description = "FenixEdu SDK Configuration")
    public interface Configuration {

        @ConfigurationProperty(key = "fenixedu.base.url", defaultValue = "http://localhost:8080",
                description = "The base URL of the FenixEdu installation (without slash)")
        public String getBaseUrl();

        @ConfigurationProperty(key = "fenixedu.oauth.consumer.key", description = "The application's OAuth Consumer Key")
        public String getOAuthConsumerKey();

        @ConfigurationProperty(key = "fenixedu.oauth.consumer.secret", description = "The application's OAuth Consumer Secret")
        public String getOAuthConsumerSecret();

        @ConfigurationProperty(key = "fenixedu.callback.url", defaultValue = "http://localhost:8080",
                description = "The callback URL of this installation (without slash)")
        public String getCallbackUrl();

        @ConfigurationProperty(key = "fenixedu.async.num.threads", defaultValue = "4",
                description = "Number of Maximum Asynchronous Threads")
        public Integer getAsyncNumThreads();

    }

    public static Configuration getConfiguration() {
        return ConfigurationInvocationHandler.getConfiguration(Configuration.class);
    }

}
