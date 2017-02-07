package br.framework.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Configuração para a camada de persistencia de dados.<br>
 * Implementação da especificação JPA : HIBERNATE <br> 
 *
 */
@EnableTransactionManagement
public class JPAConfiguration {

	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean em = new
				LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[]{"br.framework.domain"});
		
		JpaVendorAdapter adaptador = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(adaptador);
		em.setJpaProperties(additionalProperties());
		
		return em;
	}


	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
			JpaTransactionManager transactionManager = new JpaTransactionManager();
			transactionManager.setEntityManagerFactory(emf);
			return transactionManager;
	}
	
	/**
	 * Configuração da fonte de dados do sistema <br>
	 * @return DataSource configurado
	 */
	@Bean
	private DataSource dataSource() {
		DriverManagerDataSource dataSource =
				new DriverManagerDataSource();
				dataSource.setDriverClassName("com.mysql.jdbc.Driver");
				dataSource.setUrl("jdbc:mysql://localhost:3306/framework");
				dataSource.setUsername( "root" );
				dataSource.setPassword( "" );
				return dataSource;
	}
	
	
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto","create-drop");
		properties.setProperty("hibernate.dialect",	"org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		return properties;
	}
	
}
