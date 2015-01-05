/**
 * 
 */
package ru.anr.base.sampleapp.tests;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ru.anr.base.dao.BaseRepositoryFactoryBean;
import ru.anr.base.dao.repository.BaseRepository;
import ru.anr.base.sampleapp.dao.UserDao;
import ru.anr.base.tests.AbstractLocalDaoConfig;

/**
 * DAO config for local JPA tests.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 27, 2014
 *
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = { UserDao.class, BaseRepository.class }, //
repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class BaseLocalDaoConfig extends AbstractLocalDaoConfig {

    /**
     * Empty
     */
}
