package org.fenixedu.sdk;

import org.fenixedu.sdk.api.ExpendituresResources;
import org.fenixedu.sdk.api.PublicResources;
import org.fenixedu.sdk.exception.FenixEduClientException;

public interface DotClient extends DotClientBase, PublicResources, ExpendituresResources {

    PublicResources publicScope();

    ExpendituresResources expendituresScope();

    public String getAuthenticationUrl();

    public OAuthAuthorization refreshAccessToken(OAuthAuthorization oldAuthorization);

    public DotUserDetails getUserDetailsFromCode(String code) throws FenixEduClientException;

}
