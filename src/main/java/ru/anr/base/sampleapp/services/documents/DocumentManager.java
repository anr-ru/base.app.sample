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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.anr.base.sampleapp.domain.Document;
import ru.anr.base.sampleapp.domain.User;

/**
 * Document functions.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */

public interface DocumentManager {

    /**
     * Creates a document
     * 
     * @param owner
     *            Document's owner
     * @param content
     *            Content
     * @return A new stored document
     */
    Document create(User owner, String content);

    /**
     * Getting all documents for specified user with page settings
     * 
     * @param owned
     *            User - owner of document
     * @param p
     *            Page settings
     * @return Page - wrapper
     */
    Page<Document> get(User owned, Pageable p);

    /**
     * Getting a single document
     * 
     * @param id
     *            Identifier
     * @return A document
     */
    Document get(Long id);

    /**
     * Removes
     * 
     * @param document
     *            Document to remove
     */
    void remove(Document document);
}
