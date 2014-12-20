/**
 * 
 */
package ru.anr.base.sampleapp.tests;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ru.anr.base.dao.AbstractJPADaoConfig;
import ru.anr.base.dao.BaseRepositoryFactoryBean;
import ru.anr.base.sampleapp.dao.UserDao;

/**
 * Integration config (uses JNDI datasource).
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 21, 2014
 *
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = { UserDao.class }, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class BaseIntegrationDaoConfig extends AbstractJPADaoConfig {

    /**
     * Empty
     */
}
