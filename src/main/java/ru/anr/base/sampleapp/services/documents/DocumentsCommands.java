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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import ru.anr.base.domain.api.APICommand;
import ru.anr.base.domain.api.APIException;
import ru.anr.base.domain.api.models.ResponseModel;
import ru.anr.base.sampleapp.domain.ApiCodes;
import ru.anr.base.sampleapp.domain.Document;
import ru.anr.base.sampleapp.services.users.UserManager;
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
@ApiStrategy(id = "documents", version = "v1", model = DocumentModel.class)
@Secured({ "ROLE_USER" })
public class DocumentsCommands extends AbstractApiCommandStrategyImpl {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DocumentsCommands.class);

    /**
     * Document manager
     */
    @Autowired
    @Qualifier("DocumentManager")
    private DocumentManager documentManager;

    /**
     * User manager
     */
    @Autowired
    @Qualifier("UserManager")
    private UserManager userManager;

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
            Document d = documentManager.get(id);

            if (d == null) {
                ((DocumentModel) m).setId(null);
                ((DocumentModel) m).setContent("No document");
            } else {
                ((DocumentModel) m).setId(d.getId());
                ((DocumentModel) m).setContent(d.getContent());
            }

        } else {

            List<DocumentModel> listDocs = list();
            Page<Document> page = documentManager.get(userManager.getUser(), //
                    new PageRequest(cmd.getRequest().getPage(), cmd.getRequest().getPerPage()));
            DocumentModel model = new DocumentModel();
            for (Document doc : page.getContent()) {
                model = new DocumentModel();
                model.setId(doc.getId());
                model.setContent(doc.getContent());
                listDocs.add(model);
            }
            m = listDocs;
        }
        return m;
    }

    /**
     * {@inheritDoc} modification of a document
     */
    @Override
    public ResponseModel put(APICommand cmd) {

        DocumentModel dm = cmd.getRequest();
        Document d = documentManager.get(dm.getId());
        logger.info("Get: {} / {}", dm, d);
        d.setContent(dm.getContent());

        Calendar cal = null;
        if (dm.getCreated() != null) {
            cal = Calendar.getInstance(TimeZone.getTimeZone(dm.getCreated().getZone()));
            cal.setTime(Date.from(dm.getCreated().toInstant()));
        }
        d.setCreated(null);

        DocumentModel rs = new DocumentModel();
        rs.setContent(d.getContent());
        rs.setCreated(dm.getCreated());

        return rs;
    }

    /**
     * {@inheritDoc} creation of a document
     */
    @Override
    public ResponseModel post(APICommand cmd) {

        DocumentModel dm = cmd.getRequest();

        Assert.hasText(dm.getContent(), text("document.content.is.null"));
        Document d = documentManager.create(userManager.getUser(), dm.getContent());

        DocumentModel rs = new DocumentModel();
        rs.setContent(d.getContent());
        rs.setId(d.getId());

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
