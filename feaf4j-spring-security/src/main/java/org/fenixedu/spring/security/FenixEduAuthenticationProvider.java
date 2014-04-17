package org.fenixedu.spring.security;

import org.fenixedu.sdk.Authorization;
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
        FenixEduAuthenticationToken token = (FenixEduAuthenticationToken) authentication;
        logger.debug("Attempting to authenticate with credentials {}", token.getCredentials());
        Authorization authorization = (Authorization) token.getCredentials();
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(authorization.asOAuthAuthorization().getOAuthAccessToken());
        return new FenixEduAuthenticationToken(userDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return FenixEduAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
