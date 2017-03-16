package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.seusl.fas.model.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dulari Ranaweera
 *
 */
@RestController
@RequestMapping(value="/api")
public class SubjectController {
	
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	/**
	 * @return list of the subjects
	 */
	@RequestMapping(value = "/subject-list", method = RequestMethod.GET)
	public List<Subject> getSubjects() {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Subject> list = new ArrayList<Subject>();

		try {
			t = session.beginTransaction();

			list = session.createQuery("from Subject").list();
			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}
	
	/**
	 * search a subject by id , name
	 * @param subjectId
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/subject-search", method=RequestMethod.GET)
	public List<Subject> serachSubject(@RequestParam("subjectId") String subjectId, @RequestParam("name") String name) {
		
		Session  session = sessionFactory.openSession();
		Transaction t = null;
		List<Subject> list = new ArrayList<Subject>();
		
		String hql = "";
		if(subjectId != "") {
			hql = "from Subject where subjectId='" + subjectId+"'";
		}
		else if(name != "") {
			hql = "from Subject where name like '%" + name+"%'";
		}		

		try {
			t = session.beginTransaction();
			list = session.createQuery(hql).list();
			t.commit();
		} catch(HibernateException e) {
			if(t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	/**
	 * insert the subject details
	 * @param subjectId
	 * @param name
	 * @param teacher_teacherId
	 */
	@RequestMapping(value = "/subject-save", method = RequestMethod.GET)
	public void insertSubject(@RequestParam("subjectId") String subjectId, @RequestParam("name") String name,
			@RequestParam("teacher_teacherId") String teacher_teacherId) {
		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();

			Subject subject = new Subject();
			subject.setId(subjectId);
			subject.setName(name);
			subject.setTeacherId(teacher_teacherId);
			
			session.save(subject);

			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/**
	 * 
	 * update the subject
	 * @param subjectId
	 * @param name
	 * @param teacher_teacherId
	 */
	@RequestMapping(value = "/subject-update", method = RequestMethod.GET)
	public void updateSubject(@RequestParam("subjectId") String subjectId, @RequestParam("name") String name,
			@RequestParam("teacher_teacherId") String teacher_teacherId) {

		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();
			String hql = "UPDATE Subject set name=:name, teacher_teacherId = :teacher_teacherId where subjectId=:subjectId ";
			Query query = session.createQuery(hql);
			query.setParameter("name", name);
			query.setParameter("teacher_teacherId", teacher_teacherId);
			query.setParameter("subjectId", subjectId);
			

			int result = query.executeUpdate();

			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * delete the subject by id
	 * @param subjectId
	 */
	@RequestMapping(value="subject-delete", method=RequestMethod.GET)
	public void deleteSubject(@RequestParam("subjectId") String subjectId) {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try {
			t = session.beginTransaction();
			
			String hql = "DELETE FROM Subject where subjectId = :subjectId";
			Query query = session.createQuery(hql);
			query.setParameter("subjectId", subjectId);
			int result = query.executeUpdate();
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
	
	@RequestMapping(value="/allSubjectId", method=RequestMethod.GET)
	public List<String> getSubjectId() {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<String> list = new ArrayList<String>();

		try {
			t = session.beginTransaction();

			list = session.createQuery("select id from Subject").list();
			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}
}
