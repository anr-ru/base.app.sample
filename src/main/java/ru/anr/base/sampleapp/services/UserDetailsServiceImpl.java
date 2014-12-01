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
package ru.anr.base.sampleapp.services;

import javax.annotation.Resource;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ru.anr.base.dao.repository.BaseRepository;
import ru.anr.base.domain.BaseEntity;
import ru.anr.base.sampleapp.dao.UserDao;
import ru.anr.base.sampleapp.domain.User;
import ru.anr.base.services.BaseDataAwareServiceImpl;

/**
 * Description ...
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 27, 2014
 *
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl extends BaseDataAwareServiceImpl implements UserDetailsService {

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String username) {

        UserDao dao = dao();
        User u = dao.getUser(username);
        if (u == null) {
            throw new UsernameNotFoundException("user.not.found");
        }
        return new org.springframework.security.core.userdetails.User(u.getLogin(), nullSafe(u.getPassword()),
                list(new SimpleGrantedAuthority("ROLE_USER")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Resource(name = "UserDao")
    public void setDao(BaseRepository<BaseEntity> dao) {

        super.setDao(dao);
    }
}
