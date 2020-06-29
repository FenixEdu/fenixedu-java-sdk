package org.fenixedu.spring.security;

import org.fenixedu.sdk.OAuthAuthorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class FenixEduAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(FenixEduAuthenticationProvider.class);

    private UserDetailsService userDetailsService;

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final FenixEduAuthenticationToken token = (FenixEduAuthenticationToken) authentication;
        logger.debug("Attempting to authenticate with credentials {}", token.getCredentials());
        final OAuthAuthorization authorization = (OAuthAuthorization) token.getCredentials();
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authorization.getOAuthAccessToken());
        return new FenixEduAuthenticationToken(userDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return FenixEduAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
