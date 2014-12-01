/**
 * 
 */
package ru.anr.base.sampleapp.tests;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ru.anr.base.ApplicationException;
import ru.anr.base.dao.repository.BaseRepository;
import ru.anr.base.domain.BaseEntity;
import ru.anr.base.sampleapp.domain.User;
import ru.anr.base.sampleapp.services.users.UserManager;
import ru.anr.base.tests.BaseTestCase;

/**
 * Base parent for all local tests.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 27, 2014
 *
 */
@ContextConfiguration(locations = "classpath:tests-local-context.xml")
@Ignore
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
public class BaseLocalTestCase extends BaseTestCase {

    /**
     * Base dao ref
     */
    @Autowired
    @Qualifier("BaseRepository")
    protected BaseRepository<BaseEntity> dao;

    /**
     * Creation of sample entity with specified list of property name/value
     * pairs
     * 
     * @param entityClass
     *            Entity class to create
     * @param propsAndValues
     *            List of property name/value paits, like: "login",
     *            "alex","weight", 100
     * @return Entity instance, stored in database
     * 
     * @param <S>
     *            Entity class
     */
    protected <S extends BaseEntity> S create(Class<S> entityClass, Object... propsAndValues) {

        Map<String, Object> map = toMap(propsAndValues);

        S entity = inst(entityClass, new Class<?>[]{}, new Object[]{});

        for (Entry<String, Object> e : map.entrySet()) {
            try {

                PropertyUtils.setProperty(entity, e.getKey(), e.getValue());

            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                throw new ApplicationException(ex);
            }
        }
        return dao.save(entity);
    }

    /**
     * Ref to authentication manager
     */
    @Autowired
    protected AuthenticationManager authenticationManager;

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

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, password);
        Authentication r = authenticationManager.authenticate(token);

        Assert.assertTrue(r.isAuthenticated());
        SecurityContextHolder.getContext().setAuthentication(r);

        return userManager.getUser();
    }

    /**
     * Ref to password encoder
     */
    @Autowired
    protected PasswordEncoder encoder;

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

    /**
     * {@inheritDoc}
     */
    @Before
    @Override
    public void setUp() {

        super.setUp();

        SecurityContextHolder.clearContext();
    }
}
