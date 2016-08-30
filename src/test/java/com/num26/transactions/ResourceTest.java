package com.num26.transactions;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.After;
import org.junit.Before;

public abstract class ResourceTest {
    private JerseyTest jersey;

    public class Num26JerseyTest extends JerseyTest {
        public Num26JerseyTest(ResourceConfig app) {
            super(app);
        }

        @Override
        protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
            return new InMemoryTestContainerFactory();
        }

        @Override
        protected void configureClient(ClientConfig config) {
            config.register(JacksonJsonProvider.class);
        }
    }

    @Before
    public void initializeJersey() throws Exception {
        JacksonJsonProvider jacksonJson = new JacksonJsonProvider();

        ResourceConfig app = new ResourceConfig()
                .register(jacksonJson)
                .property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, "true")
                .property(ServerProperties.TRACING, "ALL")
                .property(ServerProperties.TRACING_THRESHOLD, "SUMMARY")
                .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, "true");

        setUpResources(app);
        jersey = new Num26JerseyTest(app);
        jersey.setUp();
    }

    @After
    public void teardownJersey() throws Exception {
        jersey.tearDown();
    }

    protected JerseyTest getJersey() {
        return jersey;
    }

    protected abstract void setUpResources(ResourceConfig application) throws Exception;
}
