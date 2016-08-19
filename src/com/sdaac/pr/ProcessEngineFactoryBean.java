package com.sdaac.pr;

import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessEngine;
import org.jbpm.pvm.internal.cfg.ConfigurationImpl;
import org.jbpm.pvm.internal.processengine.SpringHelper;


public class ProcessEngineFactoryBean extends SpringHelper {

	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ProcessEngine createProcessEngine() {
		processEngine = new ConfigurationImpl().springInitiated(
				applicationContext).setResource(jbpmCfg)
				.setHibernateSessionFactory(sessionFactory)
				.buildProcessEngine();
		return processEngine;
	}

}
