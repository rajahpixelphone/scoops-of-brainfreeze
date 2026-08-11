package com.scoops.brainfreeze.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * Required for Zoho Catalyst AppSail.
 * The platform injects the port via X_ZOHO_CATALYST_LISTEN_PORT.
 */
@Component
public class ServerPortCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        String port = System.getenv("X_ZOHO_CATALYST_LISTEN_PORT");
        if (port != null && !port.isEmpty()) {
            factory.setPort(Integer.parseInt(port));
        }
        // When running locally, the default server.port (8080) is used.
    }
}
