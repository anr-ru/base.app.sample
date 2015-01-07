/**
 * 
 */
package ru.anr.base.sampleapp.services.documents;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.anr.base.domain.api.MethodTypes;
import ru.anr.base.domain.api.models.RequestModel;
import ru.anr.base.sampleapp.domain.User;
import ru.anr.base.sampleapp.tests.BaseAPITestCase;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Description ...
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */

public class DocumentsCommandsTest extends BaseAPITestCase {

    /**
     * Document manager
     */
    @Autowired
    private DocumentManager documentManager;

    /**
     * Documents api
     * 
     * @param method
     *            API Method
     * @param rq
     *            Request model
     * @param params
     *            Request params
     * @param contexts
     *            Contexts if required
     * @return Response model
     */
    private String doDocuments(MethodTypes method, RequestModel rq, Map<String, ?> params, Object... contexts) {

        return doAPI("documents", "v1", method, rq, params, contexts);
    }

    /**
     * Use case: quering all documents
     */
    @Test
    public void testGetAPICommandAllDocuments() {

        /*
         * Authorised
         */
        User user = createAndAuthenticate();

        documentManager.create(user, "1111");
        documentManager.create(user, "2222");
        documentManager.create(user, "3333");

        TypeReference<List<DocumentModel>> typeDef = new TypeReference<List<DocumentModel>>() {
        };
        DocumentModel reqmod = new DocumentModel();
        reqmod.setPage(0);
        reqmod.setPerPage(2);
        Map<String, Integer> params = new HashMap<>();
        params.put("page", 0);
        params.put("per_page", 2);

        List<DocumentModel> r = json.fromStr(doDocuments(MethodTypes.Get, reqmod, params), typeDef);
        Assert.assertNotNull(r);
        Assert.assertEquals(2, r.size());

        params = new HashMap<>();
        params.put("page", 0);
        params.put("per_page", 5);
        r = json.fromStr(doDocuments(MethodTypes.Get, reqmod, params), typeDef);
        Assert.assertEquals(3, r.size());
    }

    /**
     * Test method for
     * {@link ru.anr.base.sampleapp.services.documents.DocumentsCommands#post(ru.anr.base.domain.api.APICommand)}
     * .
     */
    @Test
    public void testPost() {

        createAndAuthenticate();

        DocumentModel dm = new DocumentModel();
        dm.setContent("111111");
        DocumentModel rs = json.fromStr(doDocuments(MethodTypes.Post, dm, null), DocumentModel.class);
        Assert.assertEquals("111111", rs.getContent());
    }

    /**
     * Test method for
     * {@link ru.anr.base.sampleapp.services.documents.DocumentsCommands#put(ru.anr.base.domain.api.APICommand)}
     * .
     */
    @Test
    public void testPut() {

        createAndAuthenticate();

        DocumentModel dm = new DocumentModel();
        dm.setContent("111111");
        DocumentModel rs = json.fromStr(doDocuments(MethodTypes.Post, dm, null), DocumentModel.class);
        Assert.assertEquals("111111", rs.getContent());

        dm.setContent("222222");
        dm.setId(rs.getId());
        rs = json.fromStr(doDocuments(MethodTypes.Put, dm, null), DocumentModel.class);
        Assert.assertEquals("222222", rs.getContent());
    }

    /**
     * Test method for
     * {@link ru.anr.base.sampleapp.services.documents.DocumentsCommands#delete(ru.anr.base.domain.api.APICommand)}
     * .
     */
    @Ignore
    public void testDelete() {

        fail("Not yet implemented");
    }

}
