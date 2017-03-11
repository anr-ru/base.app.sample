/**
 * 
 */
package ru.anr.base.sampleapp.tests.dao;

import org.hsqldb.HsqlException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import ru.anr.base.ApplicationException;
import ru.anr.base.sampleapp.dao.DocumentDao;
import ru.anr.base.sampleapp.dao.UserDao;
import ru.anr.base.sampleapp.domain.Document;
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

public class BasicDaoTest extends BaseLocalTestCase {

    /**
     * Basic DAO checks
     */
    @Test
    public void test() {

        User u = create(User.class, "login", "alex", "fullName", "Alexey Romanchuk");
        Assert.assertNotNull(u.getId());

        dao.flush();

        // Check unique constraint to work
        try {

            create(User.class, "login", "alex", "fullName", "Alexey The Second");
            dao.flush();

            Assert.fail();
        } catch (Exception ex) {

            HsqlException origin = (HsqlException) new ApplicationException(ex).getMostSpecificCause();
            Assert.assertTrue(origin.getMessage().contains(
                    "integrity constraint violation: unique constraint or index violation"));
        }
    }

    /**
     * UserDao checks
     */
    @Autowired
    @Qualifier("UserDao")
    private UserDao userDao;

    /**
     * Basic DAO checks
     */
    @Test
    public void testUserDao() {

        User u = userDao.getUser("alex");
        Assert.assertNull(u);

        u = create(User.class, "login", "alex");
        u = userDao.getUser("alex");

        Assert.assertNotNull(u);

        // Another one to check search
        create(User.class, "login", "wasya");
        User x = userDao.getUser("alex");

        Assert.assertEquals(u, x);
    }

    /**
     * DocumentDao checks
     */
    @Autowired
    @Qualifier("DocumentDao")
    private DocumentDao documentDao;

    /**
     * Basic DAO checks
     */
    @Test
    public void testDocumentDao() {

        User u = create(User.class, "login", "alex");
        User x = create(User.class, "login", "wasya");

        /*
         * Using pages
         */
        Pageable p = new PageRequest(0, 10, Direction.ASC, "owner", "id");

        // Use case: no documents
        Assert.assertEquals(0, documentDao.getDocuments(u, p).getTotalElements());
        Assert.assertEquals(0, documentDao.getDocuments(x, p).getTotalElements());

        // Use case: some documents beloging two different users
        Document d1 = create(Document.class, "owner", u, "content", "Bla-bla 1");
        Document d2 = create(Document.class, "owner", u, "content", "Bla-bla 2");

        Document d3 = create(Document.class, "owner", x, "content", "Bla-bla 3");

        // Checking ownerchip
        Page<Document> pagerU = documentDao.getDocuments(u, p);
        Assert.assertEquals(2, pagerU.getTotalElements());
        Assert.assertEquals(list(d1, d2), list(pagerU.getContent()));

        Page<Document> pagerX = documentDao.getDocuments(x, p);
        Assert.assertEquals(1, pagerX.getTotalElements());
        Assert.assertEquals(list(d3), list(pagerX.getContent()));
    }
}
