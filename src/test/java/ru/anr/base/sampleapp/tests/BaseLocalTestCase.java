/**
 * 
 */
package ru.anr.base.sampleapp.tests;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;

import ru.anr.base.sampleapp.domain.User;
import ru.anr.base.sampleapp.services.users.UserManager;
import ru.anr.base.services.BaseLocalServiceTestCase;

/**
 * Base parent for all local tests.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 27, 2014
 *
 */
@ContextConfiguration(locations = "classpath:tests-local-context.xml", inheritLocations = false)
@Ignore
public class BaseLocalTestCase extends BaseLocalServiceTestCase {

    /**
     * Performs authentication and when it's successfull - sets security context
     * 
     * @param name
     *            User name
     * @param password
     *            User password
     * @return Authrorised user
     */
    protected User authenticate(String name, String password) {

        super.authenticate(new UsernamePasswordAuthenticationToken(name, password));
        return userManager.getUser();
    }

    /**
     * User manager
     */
    @Autowired
    private UserManager userManager;

    /**
     * Creates a user for given params and attempts to authenticate him
     * 
     * @param params
     *            Array of one or two strings - the first is login, the second
     *            is password. If absent - using default.
     * @return A token
     */
    protected User createAndAuthenticate(String... params) {

        String login = (params != null && params.length > 0) ? params[0] : "alex";
        String pwd = (params != null && params.length > 1) ? params[1] : "password";

        User u = userManager.createUser(login, pwd);
        u.setFullName("Sample user");

        return authenticate(login, pwd);
    }
}
