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

/**
 * API CODES Reference
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */

public final class ApiCodes {

    /**
     * Private constructor to avoid instantiation
     */
    private ApiCodes() {

    }

    /**
     * System error
     */
    public static final int API_SYSTEM_ERROR = 1;

    /**
     * User already registered
     */
    public static final int API_USER_EXISTS = 2;

    /**
     * Syntax error
     */
    public static final int API_SYNTAX_ERROR = 3;

}
