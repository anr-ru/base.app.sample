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
package ru.anr.base.sampleapp.facade;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.PersistenceUnit;
import javax.persistence.PersistenceUnits;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.anr.base.facade.ejb.EJBSpringLoader;

/**
 * Application loader
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */
@Singleton
@Startup
@PersistenceUnits({ @PersistenceUnit(name = "AppUnit/EntityManagerFactory", unitName = "AppUnit") })
public class EJBLoader extends EJBSpringLoader {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(EJBLoader.class);

    /**
     * Initialization
     */
    @Override
    @PostConstruct
    public void init() {

        super.init();

        logger.info("Sample Application started");
    }
}
