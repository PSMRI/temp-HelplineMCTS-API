/*
* AMRIT – Accessible Medical Records via Integrated Technology
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.mcts.configure;

import jakarta.persistence.EntityManagerFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.iemr.mcts.utils.config.ConfigProperties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "secondaryEntityManagerFactory", transactionManagerRef = "secondaryTransactionManager", basePackages = {
		"com.iemr.mcts.secondary.repository.*", "com.iemr.mcts.secondary.data.*" })
public class SecondaryDBConfig {
	@Bean(name = "secondaryDataSource")
	@ConfigurationProperties(prefix = "secondary.datasource")
	public DataSource dataSource() {
		PoolConfiguration p = new PoolProperties();
		p.setMaxActive(20);
		p.setMaxIdle(8);
		p.setMinIdle(3);
		p.setInitialSize(5);
		p.setMaxWait(10000);
		p.setMinEvictableIdleTimeMillis(15000);
		p.setRemoveAbandoned(true);
		p.setLogAbandoned(true);
		p.setRemoveAbandonedTimeout(600);
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		org.apache.tomcat.jdbc.pool.DataSource datasource = new org.apache.tomcat.jdbc.pool.DataSource();
		datasource.setPoolProperties(p);

		datasource.setUsername(ConfigProperties.getPropertyByName("secondary.datasource.username"));
		datasource.setPassword(ConfigProperties.getPropertyByName("secondary.datasource.password"));

		return datasource;
	}

	@Bean(name = "secondaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("secondaryDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource)
				.packages("com.iemr.mcts.secondary.data.report", "com.iemr.mcts.secondary.data.*")
				.persistenceUnit("db_reporting").build();
	}

	@Bean(name = "secondaryTransactionManager")
	public PlatformTransactionManager barTransactionManager(
			@Qualifier("secondaryEntityManagerFactory") EntityManagerFactory secondaryEntityManagerFactory) {
		return new JpaTransactionManager((jakarta.persistence.EntityManagerFactory)secondaryEntityManagerFactory);
	}
}
