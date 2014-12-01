/**
 * 
 */
package ru.anr.base.sampleapp.tests;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ru.anr.base.ApplicationException;
import ru.anr.base.dao.AbstractJPADaoConfig;
import ru.anr.base.dao.BaseRepositoryFactoryBean;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * DAO config for local JPA tests.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 27, 2014
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = { "ru.anr.base.sampleapp.dao", "ru.anr.base.dao.repository" }, //
repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class BaseLocalDaoConfig extends AbstractJPADaoConfig {

    /**
     * {@inheritDoc}
     */
    @Override
    public DataSource dataSource() {

        ComboPooledDataSource ds = new ComboPooledDataSource();

        try {

            ds.setDriverClass(getJdbcDriverClassName());
            ds.setJdbcUrl(getJdbcUrl());
            ds.setUser(getUserName());
            ds.setPassword(getPassword());

            ds.setInitialPoolSize(1);
            ds.setMinPoolSize(1);
            ds.setMaxPoolSize(3);

        } catch (PropertyVetoException ex) {
            throw new ApplicationException(ex);
        }

        return ds;
    }
}
