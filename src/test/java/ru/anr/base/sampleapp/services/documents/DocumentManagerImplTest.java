/**
 * 
 */
package ru.anr.base.sampleapp.services.documents;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import ru.anr.base.sampleapp.domain.Document;
import ru.anr.base.sampleapp.domain.User;
import ru.anr.base.sampleapp.services.users.UserManager;
import ru.anr.base.sampleapp.tests.BaseAPITestCase;

/**
 * Document manager tests/
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */

public class DocumentManagerImplTest extends BaseAPITestCase {

    /**
     * Manager to test
     */
    @Autowired
    private DocumentManager manager;

    /**
     * Creation of document
     * 
     * @param text
     *            Document's content
     * @return Document instance
     */
    private Document createDoc(String text) {

        User u = userManager.getUser();
        Assert.assertNotNull("User not authenticated", u);

        return create(Document.class, "owner", u, "content", text);
    }

    /**
     * Use case: Creation of a new document
     */
    @Test
    public void testCreate() {

        User u = createAndAuthenticate();

        Document d = manager.create(u, "The text");
        Assert.assertNotNull(d);

        Assert.assertEquals(u, d.getOwner());
        Assert.assertEquals("The text", d.getContent());
        Assert.assertNotNull(d.getId());
    }

    /**
     * UserManager
     */
    @Autowired
    private UserManager userManager;

    /**
     * Use case: Loading document by id
     */
    @Test
    public void testGet() {

        /*
         * Use case : user tries to get his own document
         */
        User u = createAndAuthenticate();
        Document d = manager.create(u, "The text");

        Document dx = manager.get(d.getId());
        Assert.assertNotNull(dx);
    }

    /**
     * Use case: Loading all documents
     */
    @Test
    public void testGetAll() {

        User u = createAndAuthenticate();

        Document d1 = createDoc("x1");
        Document d2 = createDoc("x2");
        Document d3 = createDoc("x3");

        Page<Document> p = manager.get(u, new PageRequest(0, 5));
        Assert.assertEquals(3, p.getTotalElements());
        Assert.assertTrue(p.getContent().containsAll(list(d1, d2, d3)));
    }

    /**
     * Use case : removing a
     */
    @Test
    public void testRemove() {

        createAndAuthenticate();
        createAndAuthenticate("xxx", "pwd");
        Document d2 = createDoc(null);

        Assert.assertEquals(d2, manager.get(d2.getId())); // exists
        manager.remove(d2);

        Assert.assertNull(manager.get(d2.getId())); // not
    }
}
