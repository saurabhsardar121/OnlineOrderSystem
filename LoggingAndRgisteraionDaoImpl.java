package com.ofos.registerationandlogging.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.connection.HibernateUtil;
import com.ofos.customer.entity.Coustmer;
import com.ofos.customer.entity.Role;

@Repository
public class LoggingAndRgisteraionDaoImpl implements LoggingAndRgisteraionDao {

	private final static Logger logger = Logger.getLogger(LoggingAndRgisteraionDaoImpl.class);

	@Override
	public void Registercoustomer(Coustmer coustmer) {
		logger.info(" Excecuttion start  LoggingAndRgisteraionDaoImpl :: i am register here ");
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(coustmer);
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End LoggingAndRgisteraionDaoImpl :: Registeration completed ");
		session.close();
		sessionFactory.close();
	}

	@Override
	public boolean LoggingCoustomer(Coustmer coustmer) {
		logger.info(" Excecuttion start  LoggingAndRgisteraionDaoImpl :: i am Loging here");
		boolean status = false;
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("from Coustmer c where c.email =:email and c.Passw =:Passw");
			query.setParameter("email", coustmer.getEmail());
			query.setParameter("Passw", coustmer.getPassw());
			List<Coustmer> coustmer1 = query.list();
			Iterator itr = coustmer1.iterator();
			while (itr.hasNext()) {
				Coustmer object = (Coustmer) itr.next();
				status = Boolean.TRUE;
			}
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End  LoggingAndRgisteraionDaoImpl :: Final Data ");
		session.close();
		sessionFactory.close();
		return status;
	}

	@Override
	public Long getRollIdByUserType(String userType) {
		logger.info(" Excecuttion Start  LoggingAndRgisteraionDaoImpl :: UserTye");
		Long id = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("from Role r where r.rollName=:rollName");
			query.setParameter("rollName", userType);
			List l = query.list();
			Iterator itr = l.iterator();
			while (itr.hasNext()) {
				Role role = (Role) itr.next();
				id = role.getId();
			}
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End  LoggingAndRgisteraionDaoImpl :: Final uertype ");
		session.close();
		sessionFactory.close();
		return id;
	}

	@Override
	public List<Coustmer> listCoustomer() {
		logger.info(" Excecuttion Start  LoggingAndRgisteraionDaoImpl :: view of list cusotmer data");
		List<Coustmer> list2 = new ArrayList<>();
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Coustmer c");
			list2 = query.list();
			tx.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End  LoggingAndRgisteraionDaoImpl :: Final view customer data here");
		session.close();
		sessionFactory.close();
		return list2;
	}

	@Override
	public void Deletecoustomer(Coustmer coustmer) {
		logger.info(" Excecuttion Start LoggingAndRgisteraionDaoImpl :: Final Coustmer  deleted");
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(coustmer);
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		session.close();
		sessionFactory.close();
	}
}
