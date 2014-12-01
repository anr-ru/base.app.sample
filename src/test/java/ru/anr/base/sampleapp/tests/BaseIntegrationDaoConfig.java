/**
 * 
 */
package ru.anr.base.sampleapp.tests;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ru.anr.base.dao.AbstractJPADaoConfig;
import ru.anr.base.dao.BaseRepositoryFactoryBean;

/**
 * Integration config (uses JNDI datasource).
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 21, 2014
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = { "ru.anr.base.sampleapp.dao" }, //
repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class BaseIntegrationDaoConfig extends AbstractJPADaoConfig {

    /**
     * Data source from JNDI
     */
    private DataSource jndiDataSource;

    /**
     * {@inheritDoc}
     */
    @Override
    @Bean(name = "datasource")
    public DataSource dataSource() {

        return jndiDataSource;
    }

    /**
     * @param jndiDataSource
     *            the jndiDataSource to set
     */
    public void setJndiDataSource(DataSource jndiDataSource) {

        this.jndiDataSource = jndiDataSource;
    }
}
