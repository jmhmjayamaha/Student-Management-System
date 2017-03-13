package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.seusl.fas.model.StudentFeedback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class StudentFeedbackController {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	@RequestMapping(value="/feedback" , method=RequestMethod.GET)
	public void SaveFeedBack(@RequestParam("subject") String subject , @RequestParam("message") String message) {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			
			StudentFeedback feedback = new StudentFeedback();
			feedback.setSubject(subject);
			feedback.setMessage(message);
			feedback.setDate(new Date().toString());
			
			session.save(feedback);
			
			t.commit();
		} catch(HibernateException e) {
			if(t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@RequestMapping(value="/feedback-list", method=RequestMethod.GET)
	public List<StudentFeedback> getNotification() {
		List<StudentFeedback> list = new ArrayList<StudentFeedback>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			
			list = session.createQuery("from StudentFeedback").list();
			
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
	
	@RequestMapping(value="/deleteFeedback/{id}" , method=RequestMethod.DELETE)
	public void deleteFeedback(@PathVariable("id") int id) {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try {
			t = session.beginTransaction();
			
			session.delete(session.find(StudentFeedback.class, new Integer(id)));
			
			t.commit();
		} catch(HibernateException e) {
			if(t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}
