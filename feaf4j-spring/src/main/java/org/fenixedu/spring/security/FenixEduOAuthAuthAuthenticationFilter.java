package org.fenixedu.spring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fenixedu.sdk.ApplicationConfiguration;
import org.fenixedu.sdk.FenixEduClient;
import org.fenixedu.sdk.FenixEduClientFactory;
import org.fenixedu.sdk.FenixEduUserDetails;
import org.fenixedu.spring.security.exception.FenixEduAuthenticationException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StringUtils;

public class FenixEduOAuthAuthAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ApplicationConfiguration applicationConfiguration;
    private final String sessionVariableName;

    protected FenixEduOAuthAuthAuthenticationFilter(ProviderManager providerManager, String defaultFilterProcessesUrl,
            String sessionVariableName) {
        this(providerManager, defaultFilterProcessesUrl, sessionVariableName, ApplicationConfiguration
                .fromProperties("fenixedu.properties"));
    }

    protected FenixEduOAuthAuthAuthenticationFilter(ProviderManager providerManager, String defaultFilterProcessesUrl,
            String sessionVariableName, String applicationConfigurationPropertiesFilename) {
        this(providerManager, defaultFilterProcessesUrl, sessionVariableName, ApplicationConfiguration
                .fromProperties(applicationConfigurationPropertiesFilename));
    }

    protected FenixEduOAuthAuthAuthenticationFilter(ProviderManager providerManager, String defaultFilterProcessesUrl,
            String sessionVariableName, ApplicationConfiguration applicationConfiguration) {
        super(defaultFilterProcessesUrl);
        setAuthenticationManager(providerManager);
        this.applicationConfiguration = applicationConfiguration;
        this.sessionVariableName = sessionVariableName;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        checkFailedAuthentication(request);
        FenixEduClient client = FenixEduClientFactory.fromConfig(applicationConfiguration);
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(code)) {
            response.sendRedirect(client.getAuthenticationUrl());
            return null;
        }

        FenixEduUserDetails details = client.getUserDetailsFromCode(code);

        FenixEduAuthenticationToken token = new FenixEduAuthenticationToken(details.getAuthorization());
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);

        if (this.eventPublisher != null) {
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
        }

        request.getSession(true).setAttribute(this.sessionVariableName,
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    }

    private void checkFailedAuthentication(HttpServletRequest request) {
        if (request.getParameter("error") != null) {
            throw new FenixEduAuthenticationException(request.getParameter("error"), request.getParameter("error_description"));
        }
    }
}
