/*
 * Copyright 2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package ru.anr.base.sampleapp.services.documents;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import ru.anr.base.domain.api.APICommand;
import ru.anr.base.domain.api.APIException;
import ru.anr.base.domain.api.models.ResponseModel;
import ru.anr.base.sampleapp.domain.ApiCodes;
import ru.anr.base.sampleapp.domain.Document;
import ru.anr.base.sampleapp.services.users.UserManager;
import ru.anr.base.sampleapp.services.users.UserModel;
import ru.anr.base.services.api.AbstractApiCommandStrategyImpl;
import ru.anr.base.services.api.ApiStrategy;

/**
 * Document API
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 27, 2014
 *
 */
@Component("Documents")
@ApiStrategy(id = "documents", version = "v1", model = UserModel.class)
@Secured({ "ROLE_USER" })
public class DocumentsCommands extends AbstractApiCommandStrategyImpl {

    /**
     * Document manager
     */
    @Autowired
    @Qualifier("DocumentManager")
    private DocumentManager documents;

    /**
     * User manager
     */
    @Autowired
    @Qualifier("UserManager")
    private UserManager users;

    /**
     * {@inheritDoc} query a document info
     */
    @Override
    public Object get(APICommand cmd) {

        Object m = null;

        Map<String, ?> map = cmd.getContexts();

        if (!CollectionUtils.isEmpty(map) && (map.get("id") != null)) { // all

            Long id = parse(map.get("id").toString(), Long.class);
            if (id == null) {
                throw new APIException(text("api.param.is.null", "id"), ApiCodes.API_SYNTAX_ERROR);
            }
            m = new DocumentModel();
            Document d = documents.get(id);

            ((DocumentModel) m).setId(d.getId());

        } else {
            m = list();
        }
        return m;
    }

    /**
     * {@inheritDoc} modification of a document
     */
    @Override
    public ResponseModel put(APICommand cmd) {

        DocumentModel rs = new DocumentModel();
        return rs;
    }

    /**
     * {@inheritDoc} creation of a document
     */
    @Override
    public ResponseModel post(APICommand cmd) {

        DocumentModel rs = new DocumentModel();
        return rs;
    }

    /**
     * {@inheritDoc} deletion of a document
     */
    @Override
    public ResponseModel delete(APICommand cmd) {

        return new ResponseModel(); // ok
    }
}
