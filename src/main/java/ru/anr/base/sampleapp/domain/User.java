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
package ru.anr.base.sampleapp.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import ru.anr.base.domain.BaseEntity;

/**
 * User entity.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 27, 2014
 *
 */
@Entity
@Table(name = "users", indexes = { @Index(columnList = "user_login", unique = true) })
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "custom")
public class User extends BaseEntity {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = -8257062796646105576L;

    /**
     * Login
     */
    private String login;

    /**
     * Password (password hash to be honest)
     */
    private String password;

    /**
     * User Full Name
     */
    private String fullName;

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * @return the login
     */
    @Column(name = "user_login", length = 64, unique = true)
    public String getLogin() {

        return login;
    }

    /**
     * @param login
     *            the login to set
     */
    public void setLogin(String login) {

        this.login = login;
    }

    /**
     * @return the password
     */
    @Column(name = "user_password", length = 256)
    public String getPassword() {

        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /**
     * @return the fullName
     */
    @Column(name = "user_full_name", length = 256)
    public String getFullName() {

        return fullName;
    }

    /**
     * @param fullName
     *            the fullName to set
     */
    public void setFullName(String fullName) {

        this.fullName = fullName;
    }

}
