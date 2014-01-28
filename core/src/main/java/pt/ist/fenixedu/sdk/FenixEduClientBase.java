package pt.ist.fenixedu.sdk;

import pt.ist.fenixedu.sdk.auth.Authorization;
import pt.ist.fenixedu.sdk.config.FenixEduClientConfigurationManager.Configuration;

public interface FenixEduClientBase {

    public Authorization getAuthorization();

    public Configuration getConfiguration();

}
