package com.ofos.item.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.stereotype.Repository;

import com.connection.HibernateUtil;
import com.ofos.item.entity.Item;
import com.ofos.item.entity.SelecteItem;

@Repository("itemDao")
public class ItemDaoImpl implements ItemDao {

	private final static Logger logger = Logger.getLogger(ItemDaoImpl.class);

	@Override
	public void addItem(Item item) throws Exception {
		logger.info(" Excecuttion start  ItemDaoImpl :: item add  here ");
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(item);
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End  ItemDaoImpl :: item add  here  completed");
		session.close();
		sessionFactory.close();
	}

	@Override
	public List<Item> ListItem() throws Exception {
		logger.info(" Excecuttion start  ItemDaoImpl :: ListItem show here");
		List<Item> list1 = new ArrayList<>();
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("from Item i");
			list1 = query.list();
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End ItemDaoImpl :: Finnal itemlist show here ");
		session.close();
		sessionFactory.close();
		return list1;
	}

	@Override
	public void editItem(Item item) throws Exception {
		logger.info(" Excecuttion start  ItemDaoImpl :: Item Edit here");
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(item);
			tx.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End ItemDaoImpl :: Item Edit completed ");
		session.close();
		sessionFactory.close();
	}

	@Override
	public void deleteItem(Item item) throws Exception {
		logger.info(" Excecuttion start  ItemDaoImpl :: Item Deleted here");
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(item);
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End ItemDaoImpl :: Item deleted here complelted");
		session.close();
		sessionFactory.close();
	}

	public void selectedItemdelete(SelecteItem selecteItem) throws Exception {
		logger.info(" Excecuttion start  ItemDaoImpl :: SeletedItem Delete here");
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(selecteItem);
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End  ItemDaoImpl :: SeletedItem  Delete here completed");
		session.close();
		sessionFactory.close();
	}

}
