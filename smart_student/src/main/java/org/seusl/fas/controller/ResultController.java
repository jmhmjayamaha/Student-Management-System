package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONObject;
import org.seusl.fas.dto.ResultRequest;
import org.seusl.fas.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value="saveResult" , method=RequestMethod.POST)
	public ResponseEntity<?> saveResult(@RequestBody ResultRequest result) {
		Result r = new Result();
		r.setStuId(result.getStuId());
		r.setSubjectName(result.getSubjectName());
		r.setResult(result.getResult());
		
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try{
			t = session.beginTransaction();
			
			session.save(r);
			
			t.commit();
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch(Exception e ) {
			if(t!= null) {
				t.rollback();
			}
			JSONObject json = new JSONObject();
			json.put("error", "something went wrong");    
			return new ResponseEntity<Object>(json,HttpStatus.BAD_REQUEST);
		} finally {
			session.close();
		}

	}
	
	@RequestMapping(value="/getResults")
	public List<Result> lastTenResults(@RequestParam("id") String id) {
		List<Result> list = new ArrayList<Result>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try {
			t = session.beginTransaction();
			
			list = session.createQuery("from Result").list();
			
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
