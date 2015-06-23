/**
 * 
 */
package ru.anr.base.sampleapp.services.users;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.anr.base.domain.api.APIException;
import ru.anr.base.sampleapp.domain.User;
import ru.anr.base.sampleapp.tests.BaseLocalTestCase;

/**
 * Tests for {@link UserManager}.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */

public class UserManagerImplTest extends BaseLocalTestCase {

    /**
     * Manager to test
     */
    @Autowired
    private UserManager manager;

    /**
     * Test login
     */
    private static final String LOGIN = "x";

    /**
     * Test method for
     * {@link ru.anr.base.sampleapp.services.users.UserManagerImpl#createUser(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testCreateUser() {

        /*
         * User case : user doesn't exist - creation is successful, and now we
         * can to sign-in
         */
        User u = manager.createUser(LOGIN, "y");

        User ux = authenticate(LOGIN, "y");
        Assert.assertEquals(u, ux);

        /*
         * Use case : user is already registered, throwing an exception
         */
        try {
            manager.createUser(LOGIN, "yy");
        } catch (APIException ex) {
            Assert.assertEquals(-1, ex.getErrorCode());
            Assert.assertEquals("User \"x\" already registered", ex.getMessage());
        }
    }

    /**
     * Test method for
     * {@link ru.anr.base.sampleapp.services.users.UserManagerImpl#getUser()}.
     */
    @Test
    public void testGetUser() {

        /*
         * Use case : nobody is logged, so that user is null
         */
        User u = manager.getUser();
        Assert.assertNull(u);

        /*
         * Use case : user created and authenticated
         */
        createAndAuthenticate();

        u = manager.getUser();
        Assert.assertNotNull(u);
    }

}
