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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import ru.anr.base.domain.api.APICommand;
import ru.anr.base.domain.api.models.ResponseModel;
import ru.anr.base.sampleapp.domain.User;
import ru.anr.base.services.api.AbstractApiCommandStrategyImpl;
import ru.anr.base.services.api.ApiStrategy;

/**
 * User API
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 27, 2014
 *
 */
@Component("Users")
@ApiStrategy(id = "users", version = "v1", model = UserModel.class)
public class UsersCommands extends AbstractApiCommandStrategyImpl {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(UsersCommands.class);

    /**
     * User manager
     */
    @Autowired
    @Qualifier("UserManager")
    private UserManager manager;

    /**
     * Get implementation
     * 
     * @param cmd
     *            Api command
     * @return response
     */
    @Override
    @Secured({ "ROLE_USER" })
    public ResponseModel get(APICommand cmd) {

        UserModelResponse m = new UserModelResponse();

        User u = manager.getUser(); // Never null due to Secured checking
        m.setFullName(u.getFullName());
        m.setLogin(u.getLogin());

        return m;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Secured({ "ROLE_USER" })
    public ResponseModel put(APICommand cmd) {

        UserModel m = cmd.getRequest();

        User u = manager.getUser(); // Never null due to Secured

        logger.info("Details: {} / {}", m, u);

        u.setFullName(m.getFullName());

        UserModelResponse rs = new UserModelResponse();
        rs.setFullName(u.getFullName());

        return rs;
    }

    /**
     * Authentication
     */
    @Autowired
    private AuthenticationManager authenticators;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseModel post(APICommand cmd) {

        UserModel m = cmd.getRequest();

        Assert.hasText(m.getLogin(), text("user.login.is.null"));
        Assert.hasText(m.getPassword(), text("user.password.is.null"));

        User u = manager.createUser(m.getLogin(), m.getPassword());
        u.setFullName(m.getFullName()); // if provided

        UserModelResponse rs = new UserModelResponse();
        rs.setFullName(u.getFullName());

        /*
         * If successfull - trying to log in
         */
        Authentication token =
                authenticators.authenticate(new UsernamePasswordAuthenticationToken(m.getLogin(), m.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(token);
        return rs;

    }
}
