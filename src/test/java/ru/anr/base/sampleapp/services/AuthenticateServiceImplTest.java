/**
 * 
 */
package ru.anr.base.sampleapp.services;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.anr.base.sampleapp.domain.User;
import ru.anr.base.sampleapp.tests.BaseLocalTestCase;

/**
 * Description ...
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 27, 2014
 *
 */

public class AuthenticateServiceImplTest extends BaseLocalTestCase {

    /**
     * 
     */
    private static final String PASSWORD = "password";

    /**
     * 
     */
    private static final String ALEX = "alex";

    /**
     * Ref to password encoder
     */
    @Autowired
    protected PasswordEncoder encoder;

    /**
     * Use case: user not found
     */
    @Test
    public void testUserNotFound() {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(ALEX, PASSWORD);

        try {
            authenticationManager.authenticate(token);
        } catch (BadCredentialsException ex) {
            assertException(ex, "Wrong user or password", BadCredentialsException.class);
        }
    }

    /**
     * Use case: wrong password
     */
    @Test
    public void testWrongPassword() {

        create(User.class, "login", ALEX, PASSWORD, encoder.encode(PASSWORD));

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(ALEX, "wrong");

        try {
            authenticationManager.authenticate(token);
        } catch (BadCredentialsException ex) {
            assertException(ex, "Wrong user or password", BadCredentialsException.class);
        }
    }

    /**
     * Use case: password is OK
     */
    @Test
    public void testOKPassword() {

        User u = create(User.class, "login", ALEX, PASSWORD, encoder.encode(PASSWORD));

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(ALEX, PASSWORD);

        Authentication r = authenticationManager.authenticate(token);
        Assert.assertTrue(r.isAuthenticated());
        Assert.assertEquals(u.getLogin(), r.getName());
    }

    /**
     * Use case: default authentication in tests
     */
    @Test
    public void testDefaultAuthentication() {

        createAndAuthenticate();

        Assert.assertEquals(ALEX, SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
