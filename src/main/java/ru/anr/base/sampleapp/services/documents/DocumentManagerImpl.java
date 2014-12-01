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

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import ru.anr.base.dao.repository.BaseRepository;
import ru.anr.base.domain.BaseEntity;
import ru.anr.base.sampleapp.dao.DocumentDao;
import ru.anr.base.sampleapp.domain.Document;
import ru.anr.base.sampleapp.domain.User;
import ru.anr.base.services.BaseDataAwareServiceImpl;

/**
 * Document business logic implementation.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */
@Component("DocumentManager")
public class DocumentManagerImpl extends BaseDataAwareServiceImpl implements DocumentManager {

    /**
     * {@inheritDoc}
     */
    @Override
    public Document create(User owner, String content) {

        Document d = new Document();
        d.setOwner(owner);
        d.setContent(content);

        d.setState(DocumentStates.Draft.name());
        return dao().save(d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Document get(Long id) {

        DocumentDao dao = dao();
        return dao.findOne(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Document> get(User owner, Pageable p) {

        DocumentDao dao = dao();
        return dao.getDocuments(owner, p);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Document document) {

        dao().delete(document);
    }

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     */
    @Override
    @Resource(name = "DocumentDao")
    public void setDao(BaseRepository<BaseEntity> dao) {

        super.setDao(dao);
    }
}
