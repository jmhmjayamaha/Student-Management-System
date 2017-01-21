package org.seusl.fas.controller;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.seusl.fas.model.StudentFeedback;
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
	
}
