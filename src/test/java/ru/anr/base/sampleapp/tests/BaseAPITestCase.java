/**
 * 
 */
package ru.anr.base.sampleapp.tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ru.anr.base.domain.api.APICommand;
import ru.anr.base.domain.api.MethodTypes;
import ru.anr.base.domain.api.models.RequestModel;
import ru.anr.base.services.api.APICommandFactory;
import ru.anr.base.services.serializer.Serializer;

/**
 * Description ...
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */
@Ignore
public class BaseAPITestCase extends BaseLocalTestCase {

    /**
     * API Factory
     */
    @Autowired
    private APICommandFactory factory;

    /**
     * JSONer
     */
    @Autowired
    @Qualifier("jsonSerializer")
    protected Serializer json;

    /**
     * Executes API command and parses the result
     * 
     * @param cmdId
     *            Identified of Command
     * @param version
     *            Version
     * @param method
     *            Http method
     * @param request
     *            Request Modek
     * @param contexts
     *            Context arrays ( key/value pairs, usually are parts of url in
     *            REST paradigm)
     * @return String result
     */
    protected String doAPI(String cmdId, String version, MethodTypes method, RequestModel request, Object... contexts) {

        APICommand cmd = new APICommand(cmdId, version).addRaw(json.toStr(request)).context(contexts);
        cmd.setType(method);

        APICommand r = factory.process(cmd);
        Assert.assertNotNull(r.getRawModel());

        return r.getRawModel();
    }

}
