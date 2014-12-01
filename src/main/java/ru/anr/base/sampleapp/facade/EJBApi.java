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

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.PersistenceUnit;
import javax.persistence.PersistenceUnits;

import ru.anr.base.facade.ejb.api.AbstractEJBApiCommandFactory;
import ru.anr.base.services.api.APICommandFactory;

/**
 * 
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 13, 2014
 *
 */
@Stateless(name = "EJBApi", mappedName = "ejb/EJBApi")
@Local(APICommandFactory.class)
@PersistenceUnits({ @PersistenceUnit(name = "AppUnit/EntityManagerFactory", unitName = "AppUnit") })
public class EJBApi extends AbstractEJBApiCommandFactory {

    /**
     * Empty. Just configuring EJB annotations (jndi name, test persistent
     * units)
     */
}
