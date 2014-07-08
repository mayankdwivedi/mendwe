package com.mendwe.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

import com.mendwe.model.User;

public class SessionFactoryUtility {

	private static final SessionFactory fact;
	static{
		AnnotationConfiguration configuration =new AnnotationConfiguration();
		configuration.configure("com/mendwe/cfg/hibernate.cfg.xml");
		configuration.addAnnotatedClass(User.class);
		new SchemaUpdate(configuration).execute(true, true);
		fact=configuration.buildSessionFactory();
	}
	public static SessionFactory getSessionFactory(){
		return fact;
	}
}
