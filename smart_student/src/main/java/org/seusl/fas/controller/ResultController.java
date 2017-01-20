package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.api.x.Result;

@RestController
@RequestMapping(value="/api")
public class ResultController {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	@RequestMapping(value="/result-search")
	public List<Result> getResults(@RequestParam("id") String id) {
		List<Result> list = new ArrayList<Result>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try {
			t = session.beginTransaction();
			
			list = session.createQuery("from Result where student_stuId='" + id+"'").list();
			
			t.commit();
		} catch(HibernateException e ) {
			if(t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
}
