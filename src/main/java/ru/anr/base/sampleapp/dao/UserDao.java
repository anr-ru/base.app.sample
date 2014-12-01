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

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.anr.base.dao.repository.BaseRepository;
import ru.anr.base.sampleapp.domain.User;

/**
 * An interface for User-related DAO operations.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 27, 2014
 *
 */
@Repository("UserDao")
public interface UserDao extends BaseRepository<User> {

    /**
     * Searching a user by its login (unique)
     * 
     * @param login
     *            User login
     * @return Found user or null if doesn't exist
     */
    @Query("from User where login = :l")
    User getUser(@Param("l") String login);
}
