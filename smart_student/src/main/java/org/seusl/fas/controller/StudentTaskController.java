package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.seusl.fas.model.StudentTask;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class StudentTaskController {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	@RequestMapping(value="/studentTasks" , method=RequestMethod.GET)
	public List<StudentTask> getTasks() {
		List<StudentTask> tasks = new ArrayList<StudentTask>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try {
			t = session.beginTransaction();
			tasks = session.createQuery("from StudentTask where messageType = 'new'").list();
			t.commit();
		} catch(HibernateException e) {
			if(t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return tasks;
	}
	
	@RequestMapping(value="/insertTasks" , method=RequestMethod.POST, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public void insertSubject(@RequestBody StudentTask task) {
		task.setMessage(task.getMessage());
		task.setMessageType(task.getMessageType());
		
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try{
			t = session.beginTransaction();
			
			session.save(task);
			
			t.commit();
		} catch(HibernateException e ) {
			if(t!= null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
