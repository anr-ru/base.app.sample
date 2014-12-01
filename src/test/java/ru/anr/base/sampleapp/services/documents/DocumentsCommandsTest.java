/**
 * 
 */
package ru.anr.base.sampleapp.services.documents;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import ru.anr.base.domain.api.MethodTypes;
import ru.anr.base.domain.api.models.RequestModel;
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
@Ignore
public class DocumentsCommandsTest extends BaseAPITestCase {

    /**
     * Documents api
     * 
     * @param method
     *            API Method
     * @param rq
     *            Request model
     * @param contexts
     *            Contexts if required
     * @return Response model
     */
    private String doDocuments(MethodTypes method, RequestModel rq, Object... contexts) {

        return doAPI("users", "v1", method, rq, contexts);
    }

    /**
     * Use case: quering all documents
     */
    @Test
    public void testGetAPICommandAllDocuments() {

        TypeReference<List<DocumentModel>> typeDef = new TypeReference<List<DocumentModel>>() {
        };
        List<DocumentModel> r = json.fromStr(doDocuments(MethodTypes.Get, null), typeDef);
        Assert.assertNotNull(r);
    }

    /**
     * Test method for
     * {@link ru.anr.base.sampleapp.services.documents.DocumentsCommands#post(ru.anr.base.domain.api.APICommand)}
     * .
     */
    @Test
    public void testPost() {

        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link ru.anr.base.sampleapp.services.documents.DocumentsCommands#put(ru.anr.base.domain.api.APICommand)}
     * .
     */
    @Test
    public void testPut() {

        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link ru.anr.base.sampleapp.services.documents.DocumentsCommands#delete(ru.anr.base.domain.api.APICommand)}
     * .
     */
    @Test
    public void testDelete() {

        fail("Not yet implemented");
    }

}
