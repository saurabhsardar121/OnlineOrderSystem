package com.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	public static SessionFactory getSessionFactory(){
		AnnotationConfiguration cf = new AnnotationConfiguration();
		cf.configure();
		SessionFactory sf = cf.buildSessionFactory();
		return sf;
	}
}
