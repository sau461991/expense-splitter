package com.example.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Sheet;

public class SheetDaoImpl implements SheetDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	public SheetDaoImpl() {
		
	}
	
	 public SheetDaoImpl(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }
	
	@Override
	@Transactional
	public void creatSheet(Sheet sheet) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(sheet);
	}

	@Override
	public void openSheet(int id) {
		
		//call search api here
		
	}
	public boolean searchSheet(int id) {
		
		//SessionFactory sessionFactory = sessionFactory.getCurrentSession();
		
		Session session = null;
		boolean isSheetPresent = false;
		
		try {
			
			session = sessionFactory.openSession();
			
			String hql = "FROM Sheet S WHERE S.id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id",id);
			List<Sheet> sheetList =  query.list();
			if(!sheetList.isEmpty())
				isSheetPresent = true;
			
			session.close();
			
		}catch(HibernateException e) {
			
			
		}
		
		
		
		return isSheetPresent;
	}
	
	public Integer addSheet(Sheet sheet) {
		
		Session session = null;
		Transaction transaction = null;
		Integer sheetId = null;;
		
		try
		{
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			sheetId = (Integer) session.save(sheet);
			
			transaction.commit();
			
			session.close();
			
		} catch(HibernateException e) {
			
			if (transaction != null) {
				
				transaction.rollback();
			}
			
		}
		
		
		return sheetId;
		
	}

}
