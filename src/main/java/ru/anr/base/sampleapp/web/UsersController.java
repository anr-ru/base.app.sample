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

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.anr.base.domain.api.APICommand;
import ru.anr.base.facade.web.api.AbstractAPIController;

/**
 * API 'Users' controller.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */
@RequestMapping("/api/v1")
@RestController
public class UsersController extends AbstractAPIController {

    /**
     * Get current user info
     * 
     * @return {@link ru.anr.base.sampleapp.services.users.UserModelResponse} as
     *         result
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String doGet() {

        APICommand cmd = buildAPI("users", "v1");
        return process(cmd).getRawModel();
    }

    /**
     * Creation of user
     * 
     * @param body
     *            Request body as
     *            {@link ru.anr.base.sampleapp.services.users.UserModel}
     * 
     * @return {@link ru.anr.base.sampleapp.services.users.UserModelResponse} as
     *         result
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String doCreate(@RequestBody String body) {

        APICommand cmd = buildAPI("users", "v1").addRaw(body);
        return process(cmd).getRawModel();
    }

    /**
     * Modification of user
     * 
     * @param body
     *            Request body as
     *            {@link ru.anr.base.sampleapp.services.users.UserModel}
     * 
     * @return {@link ru.anr.base.sampleapp.services.users.UserModelResponse} as
     *         result
     */
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public String doModify(@RequestBody String body) {

        APICommand cmd = buildAPI("users", "v1").addRaw(body);
        return process(cmd).getRawModel();
    }
}
