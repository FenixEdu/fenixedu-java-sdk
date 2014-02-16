package pt.ist.fenixedu.sdk;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FenixEduClientFactory {

    private static final Logger LOG = LoggerFactory.getLogger(FenixEduClientFactory.class);

    private static FenixEduClient INSTANCE;

    public static FenixEduClient getSingleton() {
        if (INSTANCE == null) {
            try {
                LOG.debug("No instance of FenixEduClient found. Creating a new one...");
                INSTANCE = new FenixEduClient();
            } catch (IOException e) {
                throw new FenixEduException("Could not read the fenixedu.config file.", e);
            }
        }
        return INSTANCE;
    }
    
}
