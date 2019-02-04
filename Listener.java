package com.sessionlisterner;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.connection.HibernateUtil;
import com.ofos.customer.dao.CoustomerDao;

@WebListener
@Controller
public class Listener implements HttpSessionListener {
    
	@Autowired
	CoustomerDao CoustomerDao;
	
	@Autowired
	HttpSession session;
	
	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		System.out.println("Session Created callback Methods ");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		session = sessionEvent.getSession();
		String email = (String) session.getAttribute("email");
		logoutEnablestatus(email);
		System.out.println("Session Destoyed callback Methods ");
	}

	private void logoutEnablestatus(String email) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("update Coustmer c set c.status=:status where c.email =:email");
		query.setParameter("status", Boolean.FALSE);
		query.setParameter("email", email);
		query.executeUpdate();
		tx.commit();
		sessionFactory.close();
		session.close();
		
	}

}
