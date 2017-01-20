package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.management.Notification;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class NotificationController {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	@RequestMapping(value="/notification-list")
	public List<Notification> getNotification() {
		List<Notification> list = new ArrayList<Notification>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			
			list = session.createQuery("from Notification").list();
			
			t.commit();
		} catch(HibernateException e) {
			if(t != null) {
				t.rollback();
			}
		} finally {
			session.close();
		}
		
		return list;
	}
}
