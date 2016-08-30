package com.num26.transactions.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class JerseyInitialization extends ResourceConfig {
    /**
     * Register JAX-RS application components.
     */
    public JerseyInitialization() {
        this.property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, "true");
        this.packages("com.num26.transactions");
    }
}
