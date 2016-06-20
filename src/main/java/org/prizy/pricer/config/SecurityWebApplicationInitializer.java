package org.prizy.pricer.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer
      extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(PortalWebAppSecureConfig.class);
    }
}