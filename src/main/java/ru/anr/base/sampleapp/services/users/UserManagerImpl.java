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

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ru.anr.base.dao.repository.BaseRepository;
import ru.anr.base.domain.BaseEntity;
import ru.anr.base.domain.api.APIException;
import ru.anr.base.sampleapp.dao.UserDao;
import ru.anr.base.sampleapp.domain.ApiCodes;
import ru.anr.base.sampleapp.domain.User;
import ru.anr.base.sampleapp.domain.UserStates;
import ru.anr.base.services.BaseDataAwareServiceImpl;

/**
 * Implementation for User-related business logic.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */
@Component("UserManager")
public class UserManagerImpl extends BaseDataAwareServiceImpl implements UserManager {

    /**
     * Password encoder
     */
    @Autowired
    private PasswordEncoder encoder;

    /**
     * {@inheritDoc}
     */
    @Override
    public User createUser(String login, String password) {

        UserDao dao = dao();
        User u = dao.getUser(login);

        if (u != null) {
            throw new APIException(text("api.user.registered", login), ApiCodes.API_USER_EXISTS);
        }

        u = new User();

        u.setLogin(login);
        u.setPassword(encoder.encode(password));
        u.setState(UserStates.Active.name());

        return dao.saveAndFlush(u);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser() {

        User u = null;
        Authentication token = SecurityContextHolder.getContext().getAuthentication();

        if (token != null) {

            UserDao dao = dao();
            u = dao.getUser(token.getName());
        }
        return u;
    }

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     */
    @Override
    @Resource(name = "UserDao")
    public void setDao(BaseRepository<BaseEntity> dao) {

        super.setDao(dao);
    }
}
