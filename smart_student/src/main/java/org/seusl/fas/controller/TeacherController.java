package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.seusl.fas.model.Teacher;
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
public class TeacherController {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	/**
	 * @return list of the teachers
	 */
	@RequestMapping(value = "/teacher-list", method = RequestMethod.GET)
	public List<Teacher> getTeachers() {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Teacher> list = new ArrayList<Teacher>();

		try {
			t = session.beginTransaction();

			list = session.createQuery("from Teacher").list();
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
	
	@RequestMapping(value="/teacher-search", method=RequestMethod.GET)
	public List<Teacher> serachTeacher(@RequestParam("teacherId") String teacherId, @RequestParam("name") String name,
			@RequestParam("telNo") String telNo,
			@RequestParam("email") String email) {
		
		Session  session = sessionFactory.openSession();
		Transaction t = null;
		List<Teacher> list = new ArrayList<Teacher>();
		
		String hql = "";
		if(teacherId != "") {
			hql = "from Teacher where teacherId='" + teacherId+"'";
		}
		else if(name != "") {
			hql = "from Teacher where name like '%" + name+"%'";
		}
		else if(telNo != "") {
			hql = "from Teacher where telNo = '" + telNo+"'";
		}
		else if(email != "") {
			hql = "from Teacher where email = '" + email+"'";
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
	 * Save a Teacher
	 * @param teacherId
	 * @param name
	 * @param address
	 * @param telNo
	 * @param email
	 * @param qualification
	 */
	@RequestMapping(value = "/teacher-save", method = RequestMethod.GET)
	public void insertTeacher(@RequestParam("teacherId") String teacherId, @RequestParam("name") String name,
			@RequestParam("address") String address, @RequestParam("telNo") String telNo,
			@RequestParam("email") String email, @RequestParam("qualification") String qualification) {
		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();

			Teacher teacher = new Teacher();
			teacher.setId(teacherId);
			teacher.setName(name);
			teacher.setAddress(address);
			teacher.setTelNo(telNo);
			teacher.setEmail(email);
			teacher.setQualification(qualification);

			session.save(teacher);

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
	 * update a teacher 
	 * @param teacherId
	 * @param name
	 * @param address
	 * @param telNo
	 * @param email
	 * @param qualification
	 */
	@RequestMapping(value = "/teacher-update", method = RequestMethod.GET)
	public void updateTeacher(@RequestParam("teacherId") String teacherId, @RequestParam("name") String name,
			@RequestParam("address") String address, @RequestParam("telNo") String telNo,
			@RequestParam("email") String email, @RequestParam("qualification") String qualification) {

		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();
			String hql = "UPDATE Teacher set name=:name, address=:address,telNo=:telNo, email=:email,qualification=:qualification where teacherId=:teacherId ";
			Query query = session.createQuery(hql);
			query.setParameter("name", name);
			query.setParameter("address", address);
			query.setParameter("telNo", telNo);
			query.setParameter("email", email);
			query.setParameter("qualification", qualification);
			query.setParameter("teacherId", teacherId);

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
	 * delete a teacher by teacher id
	 * @param teacherId
	 */
	@RequestMapping(value="teacher-delete", method=RequestMethod.GET)
	public void deleteTeacher(@RequestParam("teacherId") String teacherId) {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try {
			t = session.beginTransaction();
			
			String hql = "DELETE FROM Teacher where teacherId = :teacherId";
			Query query = session.createQuery(hql);
			query.setParameter("teacherId", teacherId);
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
}
