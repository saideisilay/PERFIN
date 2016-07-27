package edu.iu.perfin.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;

import edu.iu.perfin.model.Account;
import edu.iu.perfin.model.BankCards;
import edu.iu.perfin.model.ConstIncomeExpense;
import edu.iu.perfin.model.Constants;
import edu.iu.perfin.model.RecordIncomeExpense;
import edu.iu.perfin.model.User;

@Configuration
public class EBeanInitializer implements InitializingBean {

	private Logger logger = Logger.getLogger(EBeanInitializer.class);

	//@Autowired UserProvider userProvider;
	@Autowired(required=false) DataSource dataSource;//bunu poolconfig classindan alabiliyoruz.

	static ServerConfig config = new ServerConfig();

	public void init() throws Exception {
		logger.info("PROJECT PERFIN INITIALIZED");

		config.setName("perfin-ebean-config");

		//String jndiName = ConfigProp.getValue("db.jndi.name", "idmserviceTestPool");
		//logger.info("Tomcat icinde tanimli olan "+jndiName +" datasource kullaniliyor.");
		//config.setDataSourceJndiName(jndiName);

		config.setDatabaseBooleanTrue("1");
		config.setDatabaseBooleanFalse("0");
		config.setDatabasePlatformName("oracle");
		
		config.setDataSource(dataSource);
		
		// specify a JNDI DataSource
		// config.setDataSourceJndiName("someJndiDataSourceName");

		// set DDL options...
		config.setDdlGenerate(true);
		config.setDdlRun(false);  // To generate tables on DB
		config.setAutoCommitMode(false);
		//config.setDebugSql(true);
		config.setDefaultServer(true);
		config.setRegister(true);
		config.addPackage("edu.iu.perfin.model");
		//config.setCurrentUserProvider(userProvider);

		// Reference Models
		config.addClass(User.class);
		config.addClass(Account.class);
		config.addClass(Constants.class);
		config.addClass(BankCards.class);
		config.addClass(ConstIncomeExpense.class);
		config.addClass(RecordIncomeExpense.class);
		EbeanServerFactory.create(config);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

}
