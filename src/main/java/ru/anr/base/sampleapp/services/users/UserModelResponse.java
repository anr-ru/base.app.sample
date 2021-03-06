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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.anr.base.domain.api.models.ResponseModel;

/**
 * API Response Model for User
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 27, 2014
 *
 */
@XmlRootElement(name = "user")
public class UserModelResponse extends ResponseModel {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = -923967973041064233L;

    /**
     * User's full name
     */
    private String fullName;

    /**
     * User's login
     */
    private String login;

    /**
     * @return the fullName
     */
    @XmlElement(name = "full_name")
    public String getFullName() {

        return fullName;
    }

    /**
     * @return the login
     */
    @XmlAttribute(name = "user_login")
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
     * @param fullName
     *            the fullName to set
     */
    public void setFullName(String fullName) {

        this.fullName = fullName;
    }

}
