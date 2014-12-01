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
package ru.anr.base.sampleapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.anr.base.dao.repository.BaseRepository;
import ru.anr.base.sampleapp.domain.Document;
import ru.anr.base.sampleapp.domain.User;

/**
 * Dao bean for Document manupulations.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 27, 2014
 *
 */
@Repository("DocumentDao")
public interface DocumentDao extends BaseRepository<Document> {

    /**
     * Loading all documents owned by specified user
     * 
     * @param owner
     *            Owner of document
     * @param p
     *            Page params
     * @return List of documents
     */
    @Query(countQuery = "select count(*) from Document d where d.owner = :user", value = "from Document d where d.owner = :user")
    Page<Document> getDocuments(@Param("user") User owner, Pageable p);
}
