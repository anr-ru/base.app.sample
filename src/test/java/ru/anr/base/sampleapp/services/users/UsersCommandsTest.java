/**
 * 
 */
package ru.anr.base.sampleapp.services.users;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import ru.anr.base.domain.api.MethodTypes;
import ru.anr.base.domain.api.models.RequestModel;
import ru.anr.base.sampleapp.domain.User;
import ru.anr.base.sampleapp.tests.BaseAPITestCase;

/**
 * User API Tests.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 27, 2014
 *
 */

public class UsersCommandsTest extends BaseAPITestCase {

    /**
     * 
     */
    private static final String WASYA_PUPKIN = "Wasya Pupkin";

    /**
     * Users api
     * 
     * @param method
     *            API Method
     * @param rq
     *            Request model
     * @return Response model
     * 
     */
    private String doUsers(MethodTypes method, RequestModel rq) {

        return doAPI("users", "v1", method, rq, null);
    }

    /**
     * Use case : quering a current user details
     */
    @Test
    public void testUsersGET() {

        /*
         * Not authorised
         */
        try {

            doUsers(MethodTypes.Get, null);
            Assert.fail();

        } catch (AuthenticationCredentialsNotFoundException ex) {
            Assert.assertNotNull(ex);
        }

        /*
         * Authorised
         */
        createAndAuthenticate();

        UserModelResponse rs = json.fromStr(doUsers(MethodTypes.Get, null), UserModelResponse.class);
        Assert.assertEquals("Sample user", rs.getFullName());
    }

    /**
     * Use case : User changes his details (full name)
     */
    @Test
    public void testUsersPUT() {

        UserModel m = new UserModel();
        m.setFullName(WASYA_PUPKIN);

        /*
         * Authorised
         */
        createAndAuthenticate();

        UserModelResponse rs = json.fromStr(doUsers(MethodTypes.Put, m), UserModelResponse.class);
        Assert.assertEquals(WASYA_PUPKIN, rs.getFullName());

        /*
         * Query again
         */
        rs = json.fromStr(doUsers(MethodTypes.Get, m), UserModelResponse.class);
        Assert.assertEquals(WASYA_PUPKIN, rs.getFullName());
    }

    /**
     * User manager
     */
    @Autowired
    private UserManager userManager;

    /**
     * Use case : registering a new user
     */
    @Test
    public void testUsersPOST() {

        UserModel m = new UserModel();
        m.setFullName(WASYA_PUPKIN);
        m.setLogin("xxx");
        m.setPassword("pwd");

        UserModelResponse rs = json.fromStr(doUsers(MethodTypes.Post, m), UserModelResponse.class);
        Assert.assertEquals(WASYA_PUPKIN, rs.getFullName());

        User u = userManager.getUser();
        Assert.assertNotNull(u);
        Assert.assertEquals("xxx", u.getLogin());
        Assert.assertEquals(WASYA_PUPKIN, u.getFullName());

        /*
         * Empty data provided:
         */
        m = new UserModel();

        try {

            doUsers(MethodTypes.Post, m);
            Assert.fail();

        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("User login must be provided", ex.getMessage());
        }

    }
}
