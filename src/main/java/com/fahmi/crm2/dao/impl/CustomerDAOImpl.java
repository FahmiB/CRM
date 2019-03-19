package com.fahmi.crm2.dao.impl;


import com.fahmi.crm2.dao.CustomerDAO;
import com.fahmi.crm2.entites.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Customer> getCustomers() {

        // get current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // create query to get customers
        Query<Customer> theQuery = session.createQuery("from Customer", Customer.class);

        // execute query and get its result
        return theQuery.getResultList();
    }
}
