package org.fenixedu.spring.security;

import java.util.Collections;

import org.fenixedu.sdk.OAuthAuthorization;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class FenixEduAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -4692065554679615204L;

    private OAuthAuthorization userAuthorization;
    private UserDetails userDetails;

    public FenixEduAuthenticationToken(OAuthAuthorization userAuthorization) {
        super(Collections.singletonList(new SimpleGrantedAuthority("USER")));
        this.userAuthorization = userAuthorization;
        setAuthenticated(true);
    }

    public FenixEduAuthenticationToken(UserDetails userDetails) {
        super(userDetails.getAuthorities());
        this.userDetails = userDetails;
        setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public Object getCredentials() {
        return userAuthorization;
    }

}
