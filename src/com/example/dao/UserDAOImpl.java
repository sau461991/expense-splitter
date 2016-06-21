package com.example.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
    private SessionFactory sessionFactory;
 
    public UserDAOImpl() {
         
    }
     
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	
    @Override
    @Transactional
    public void saveOrUpdate(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

}
