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

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ru.anr.base.dao.BaseJTADaoConfig;
import ru.anr.base.dao.BaseRepositoryFactoryBean;

/**
 * JTA DAO configuration.
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = { "ru.anr.base.sampleapp.dao" }, //
repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableTransactionManagement
public class DaoConfig extends BaseJTADaoConfig {
}
