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
package ru.anr.base.sampleapp.services.users;

import ru.anr.base.sampleapp.domain.User;

/**
 * UserManager interface - user-related business functions.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */

public interface UserManager {

    /**
     * Creation (Registration) of a new user
     * 
     * @param login
     *            Provided login
     * @param password
     *            Provided password
     * @return A user created and stored. Throws
     *         {@link ru.anr.base.domain.api.APIException} with code =
     *         {@link ru.anr.base.sampleapp.domain.ApiCodes#API_USER_EXISTS} if
     *         a user with the same login already exist.
     */
    User createUser(String login, String password);

    /**
     * Read a current authenticated user from Security context
     * 
     * @return User object or null if not logged
     */
    User getUser();
}
