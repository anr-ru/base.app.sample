/**
 * 
 */
package ru.anr.base.sampleapp.tests;

import org.junit.Before;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import ru.anr.base.facade.web.api.RestClient;
import ru.anr.base.services.serializer.Serializer;
import ru.anr.base.tests.AbstractGlassfishWebTestCase;

/**
 * Base parent for all integration tests.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */
@ContextConfiguration(locations = "classpath:tests-integration-context.xml")
@Ignore
public class BaseIntegrationTestCase extends AbstractGlassfishWebTestCase {

    /**
     * REST Client
     */
    protected RestClient client;

    /**
     * {@inheritDoc}
     */
    @Before
    @Override
    public void setUp() {

        super.setUp();
        client = new RestClient();
    }

    /**
     * JSONer
     */
    @Autowired
    @Qualifier("jsonSerializer")
    protected Serializer json;
}
