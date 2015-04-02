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
package ru.anr.base.sampleapp.web;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.anr.base.domain.api.APICommand;
import ru.anr.base.facade.web.api.AbstractAPIController;

/**
 * 
 * API 'Documents' controller.
 *
 *
 * @author Aleksey Melkov
 * @created Dec 8, 2014
 *
 */
@RequestMapping("/api/v1")
@RestController
public class DocumentsController extends AbstractAPIController {

    /**
     * Get document info by id
     * 
     * @param id
     *            - id
     * @param params
     *            - Parameters
     * @return {@link ru.anr.base.sampleapp.services.documents.DocumentModel} as
     *         result
     */
    @RequestMapping(value = "/documents/{id}", method = RequestMethod.GET)
    public String doGet(@PathVariable String id, @RequestParam Map<String, String> params) {

        APICommand cmd = buildAPI("documents", "v1").context("id", id).params(params);
        return process(cmd).getRawModel();
    }

    /**
     * Get all user documents
     * 
     * @param params
     *            - Parameters
     * @return {@link List
     *         <ru.anr.base.sampleapp.services.documents.DocumentModel>} as
     *         result
     */
    @RequestMapping(value = "/documents", method = RequestMethod.GET)
    public String doGet(@RequestParam Map<String, String> params) {

        APICommand cmd = buildAPI("documents", "v1").params(params);
        return process(cmd).getRawModel();
    }

    /**
     * Creation of document
     * 
     * @param body
     *            Request body as
     *            {@link ru.anr.base.sampleapp.services.documents.DocumentModel}
     * 
     * @return {@link ru.anr.base.sampleapp.services.documents.DocumentModel} as
     *         result
     */
    @RequestMapping(value = "/documents", method = RequestMethod.POST)
    public String doCreate(@RequestBody String body) {

        APICommand cmd = buildAPI("documents", "v1").addRaw(body);
        return process(cmd).getRawModel();
    }

    /**
     * Modification of document
     * 
     * @param body
     *            Request body as
     *            {@link ru.anr.base.sampleapp.services.documents.DocumentModel}
     * 
     * @return {@link ru.anr.base.sampleapp.services.documents.DocumentModel} as
     *         result
     */
    @RequestMapping(value = "/documents", method = RequestMethod.PUT)
    public String doModify(@RequestBody String body) {

        APICommand cmd = buildAPI("documents", "v1").addRaw(body);
        return process(cmd).getRawModel();
    }
}
