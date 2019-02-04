package com.ofos.customer.dao;

import java.util.ArrayList;
import java.util.Date;
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
import com.ofos.item.dto.SelecteItemDto;
import com.ofos.item.entity.Item;
import com.ofos.item.entity.SelecteItem;

@Repository
public class CoustomerDaoImpl implements CoustomerDao {

	private final static Logger logger = Logger.getLogger(CoustomerDaoImpl.class);

	@Override
	public boolean InsertedSelectedItems(Item item, Long cid) {
		logger.info(" Excecuttion start  CoustomerDaoImpl :: Insert dataitem  here");
		Long result = 0l;
		boolean status = false;
		SelecteItem selecteItem = new SelecteItem();
		selecteItem.setCoustmer_id(cid);
		selecteItem.setItemname(item.getItemname());
		selecteItem.setPrice(item.getPrice());
		selecteItem.setItemId(item.getId());
		selecteItem.setIs_active(true);
		selecteItem.setBuy_event(new Date());
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			result = (Long) session.save(selecteItem);
			if (result != 0L) {
				status = Boolean.TRUE; // true
			}
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End CoustomerDaoImpl :: Insert dataitem  here");
		session.close();
		sessionFactory.close();
		return status;
	}

	@Override
	public Item getItemById(Long id) throws Exception {

		logger.info(" Excecuttion start  CoustomerDaoImpl :: Insert ID  here" + id);
		Item item = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("from Item  where id=:id");
			query.setParameter("id", id);
			item = (Item) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End  CoustomerDaoImpl :: Insert ID  here");
		session.close();
		sessionFactory.close();
		return item;
	}

	@Override
	public Coustmer getCustumerByEmailId(String userName) throws Exception {
		logger.info(" Excecuttion start  CoustomerDaoImpl :: Feteching Cusotmer here");
		Coustmer coustmer = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("from Coustmer c where c.email = :email ");
			query.setParameter("email", userName);
			List<Coustmer> list = (List<Coustmer>)query.list();
			System.out.println("Where condition using hibernatre data " + list);
			Iterator itr = list.iterator();
			while (itr.hasNext()) {
				coustmer = (Coustmer) itr.next();
				coustmer.getId();
			}
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End  CoustomerDaoImpl :: Feteching completed  here");
		session.close();
		sessionFactory.close();
		return coustmer;
	}

	@Override
	public Long getLoggedInUserIdByEmail(String email) {
		logger.info(" Excecuttion Start  CoustomerDaoImpl :: Feteching Customer loging  here");
		Long id = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("from Coustmer c where c.email = :email ");
			query.setParameter("email", email);
			List<Coustmer> list = (List<Coustmer>) query.list();
			System.out.println("Where condition using hibernatre data " + list);
			for (Coustmer coustmer : list) {
				id = coustmer.getId();
				break;
			}
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End CoustomerDaoImpl :: Feteching Customer loging completed  here");
		session.close();
		sessionFactory.close();
		return id;
	}

	@Override
	public List<SelecteItemDto> getAllSelectedItems(Long[] allselids, Long loggedInUserId) throws Exception {
		logger.info(" Excecuttion Start  CoustomerDaoImpl :: Selecting  item here");
		List<SelecteItemDto> selecteItemDtoList = new ArrayList<>();
		List<SelecteItem> selecteItemList = new ArrayList<>();
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			for (Long id : allselids) {
				sessionFactory = HibernateUtil.getSessionFactory();
				session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();
				Query query = session.createQuery(
						"from SelecteItem s  where s.itemId=:itemId and s.coustmer_id=:coustmer_id and s.is_active=:is_active");
				query.setParameter("itemId", id);
				query.setParameter("coustmer_id", loggedInUserId);
				query.setParameter("is_active", Boolean.TRUE);
				List<SelecteItem> selectedItem = query.list();
				selecteItemList.addAll(selectedItem);
				System.out.println("selecteItemList" + selecteItemList);
				transaction.commit();
			}
			selecteItemDtoList = prepareMOdelForSelectedData(selecteItemList);
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		session.close();
		sessionFactory.close();
		logger.info(" Excecuttion End CoustomerDaoImpl :: selecting data  here" + selecteItemDtoList);
		return selecteItemDtoList;
	}

	@Override
	public String getUserTypeByLoggedInUserId(Long loggedInUserId) {
		logger.info(" Excecuttion Start CoustomerDaoImpl :: fecting by role name");
		String status = "";
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("from Role r where r.id =:id");
			query.setParameter("id", loggedInUserId);
			List<Role> role = query.list();
			Iterator itr = role.iterator();
			while (itr.hasNext()) {
				Role role1 = (Role) itr.next();
				status = role1.getRollName();
			}
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End CoustomerDaoImpl :: fetching the rolename completed  here");
		session.close();
		sessionFactory.close();
		return status;
	}

	private List<SelecteItemDto> prepareMOdelForSelectedData(List<SelecteItem> selectedItemList) {
		List<SelecteItemDto> SelecteItemDtoList = new ArrayList();
		for (SelecteItem selecteItem : selectedItemList) {
			SelecteItemDto selecteItemDto = new SelecteItemDto();
			selecteItemDto.setCoustmer_id(selecteItem.getCoustmer_id());
			selecteItemDto.setId(selecteItem.getId());
			selecteItemDto.setItemId(selecteItem.getItemId());
			selecteItemDto.setItemname(selecteItem.getItemname());
			selecteItemDto.setPrice(selecteItem.getPrice());
			selecteItemDto.setTotalprice(selecteItem.getTotalprice());
			SelecteItemDtoList.add(selecteItemDto);
		}
		return SelecteItemDtoList;
	}

	public Coustmer findCustumerById(Long id) {
		logger.info(" Excecuttion Start CoustomerDaoImpl :: fetcing cusotmer id");
		Coustmer coustmer = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("from Coustmer c where c.id = :id");
			query.setParameter("id", id);
			coustmer = (Coustmer) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End CoustomerDaoImpl :: Fetching customer id completed here");
		session.close();
		sessionFactory.close();
		return coustmer;
	}

	@Override
	public List<SelecteItem> getSelectedItemById(Long cid) throws Exception {
		logger.info(" Excecuttion Start CoustomerDaoImpl ::  active item here");
		List<SelecteItem> selecteItem = new ArrayList<>();
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session
					.createQuery("from SelecteItem s  where s.coustmer_id=:coustmer_id and s.is_active= :is_active ");
			query.setParameter("coustmer_id", cid);
			query.setParameter("is_active", true);
			selecteItem = query.list();
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End CoustomerDaoImpl :: upated for the active item here" + selecteItem);
		session.close();
		sessionFactory.close();
		return selecteItem;
	}


	@Override
	public List<Item> getCurrentAvailableItemList(List<Long> selectedItemIds) {
		logger.info(" Excecuttion Start CoustomerDaoImpl :: current avliavleitem list");
		List<Item> listItems = new ArrayList<>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Item item = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		for (Long itemId : selectedItemIds) {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Item i where i.id=:id");
			query.setParameter("id", itemId);
			item = (Item) query.uniqueResult();
			transaction.commit();
			updateavilabelcoutInItemTable((item.getAvilabelstock() - 1L), item.getId());

		}
		logger.info(" Excecuttion End CoustomerDaoImpl :: current avliavleitem list fetching");
		session.close();
		sessionFactory.close();
		return listItems;
	}

	private void updateavilabelcoutInItemTable(Long count, Long selectedItemId) {

		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("update Item i set i.avilabelstock=:avilabelstock where i.id =:id");
			query.setParameter("avilabelstock", count);
			query.setParameter("id", selectedItemId);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		session.close();
		sessionFactory.close();
	}

	@Override
	public void updateStatusForSelectedItem(List<Long> selectedItemIds, Long cid) {
		logger.info(" Excecuttion Start CoustomerDaoImpl :: updated list of selected item");
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		try {
			for (Long selectedItemId : selectedItemIds) {

				tx = session.beginTransaction();
				Query query = session.createQuery(
						" update SelecteItem s set s.is_active=false where s.itemId=:itemId and s.coustmer_id=:coustmer_id ");
				query.setParameter("itemId", selectedItemId);
				query.setParameter("coustmer_id", cid);
				int a = query.executeUpdate();
				System.out.println("Updated ++" + a);
				tx.commit();
			}
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		session.close();
		sessionFactory.close();
	}

	@Override
	public List<SelecteItem> viewOfallorederdteails() {
		logger.info(" Excecuttion Start CoustomerDaoImpl :: view of order details");
		List<SelecteItem> listitems = new ArrayList<>();
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("from SelecteItem s");
			List<SelecteItem> selectedItem = query.list();
			listitems.addAll(selectedItem);
			transaction.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		session.close();
		sessionFactory.close();
		return listitems;
	}

	@Override
	public void enablestatus(String useremail) {
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
		 sessionFactory = HibernateUtil.getSessionFactory();
		  session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("update Coustmer c set c.status=:status where c.email =:email");
			query.setParameter("status", Boolean.TRUE);
			query.setParameter("email", useremail);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			logger.error(" Eception Occured  CoustomerDaoImpl::loggerMessageTesting  ", e);
		}
		logger.info(" Excecuttion End  CoustomerDaoImpl :: Insert ID  here");
		session.close();
		sessionFactory.close();
		
	}


}
