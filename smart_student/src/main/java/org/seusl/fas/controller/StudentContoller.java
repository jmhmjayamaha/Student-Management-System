package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.seusl.fas.model.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dulari Ranaweera
 *
 */
@RestController
@RequestMapping(value = "/api")
public class StudentContoller {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	/**
	 * @return the list of students
	 */
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public List<Student> getStudents() {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Student> list = new ArrayList<Student>();

		try {
			t = session.beginTransaction();

			list = session.createQuery("from Student").list();
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
	 * search student by stuId, name , telno, email , academic year
	 * @param stuId
	 * @param name
	 * @param telNo
	 * @param email
	 * @param acedemicYear
	 * @return
	 */
	@RequestMapping(value="/student-search", method=RequestMethod.GET)
	public List<Student> serachStudent(@RequestParam("stuId") String stuId, @RequestParam("name") String name,
			@RequestParam("telNo") String telNo,
			@RequestParam("email") String email, @RequestParam("acedemicYear") String acedemicYear) {
		
		Session  session = sessionFactory.openSession();
		Transaction t = null;
		List<Student> list = new ArrayList<Student>();
		
		String hql = "";
		if(stuId != "") {
			hql = "from Student where stuId='" + stuId+"'";
		}
		else if(name != "") {
			hql = "from Student where name like '%" + name+"%'";
		}
		else if(telNo != "") {
			hql = "from Student where telNo = '" + telNo+"'";
		}
		else if(email != "") {
			hql = "from Student where email = '" + email+"'";
		}
		else if(acedemicYear != "") {
			hql = "from Student where acedemicYear = '" + acedemicYear+"'";
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
	 * @param stuId
	 * @param name
	 * @param address
	 * @param telNo
	 * @param email
	 * @param acedemicYear
	 *            save a student details
	 */
	@RequestMapping(value = "/student-save", method = RequestMethod.GET)
	public void insertStudent(@RequestParam("stuId") String stuId, @RequestParam("name") String name,
			@RequestParam("address") String address, @RequestParam("telNo") String telNo,
			@RequestParam("email") String email, @RequestParam("acedemicYear") String acedemicYear) {
		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();

			Student student = new Student();
			student.setId(stuId);
			student.setName(name);
			student.setAddress(address);
			student.setTelNo(telNo);
			student.setEmail(email);
			student.setAcedemicYear(acedemicYear);

			session.save(student);

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
	 * @param stuId
	 * @param name
	 * @param address
	 * @param telNo
	 * @param email
	 * @param acedemicYear
	 *            update the student
	 */
	@RequestMapping(value = "/student-update", method = RequestMethod.GET)
	public void updateStudent(@RequestParam("stuId") String stuId, @RequestParam("name") String name,
			@RequestParam("address") String address, @RequestParam("telNo") String telNo,
			@RequestParam("email") String email, @RequestParam("acedemicYear") String acedemicYear) {

		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();
			String hql = "UPDATE Student set name=:name, address=:address,telNo=:telNo, email=:email,acedemicYear=:acedemicYear where stuId=:stuId ";
			Query query = session.createQuery(hql);
			query.setParameter("name", name);
			query.setParameter("address", address);
			query.setParameter("telNo", telNo);
			query.setParameter("email", email);
			query.setParameter("acedemicYear", acedemicYear);
			query.setParameter("stuId", stuId);

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
	 * delete student by id
	 * @param stuId
	 */
	@RequestMapping(value="student-delete", method=RequestMethod.GET)
	public void deleteStudent(@RequestParam("stuId") String stuId) {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try {
			t = session.beginTransaction();
			
			String hql = "DELETE FROM Student where stuId = :stuId";
			Query query = session.createQuery(hql);
			query.setParameter("stuId", stuId);
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
	
	@RequestMapping(value="/allStudentId", method=RequestMethod.GET)
	public List<String> getStudentId() {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<String> list = new ArrayList<String>();

		try {
			t = session.beginTransaction();

			list = session.createQuery("select id from Student").list();
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
