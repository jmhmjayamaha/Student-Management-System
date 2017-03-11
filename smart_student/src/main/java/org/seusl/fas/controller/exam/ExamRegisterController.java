package org.seusl.fas.controller.exam;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.seusl.fas.dto.ExamRegisterRequest;
import org.seusl.fas.model.ExamRegister;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/info")
public class ExamRegisterController {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	@RequestMapping(value="/saveRegistration", method=RequestMethod.POST)
	public void saveRegistration(@RequestBody ExamRegisterRequest exam) {	
		Session session = sessionFactory.openSession();
		Transaction t = null;

		ExamRegister register = new ExamRegister();
		register.setName(exam.getName());
		register.setRegNo(exam.getRegNo());
		register.setYear(exam.getYear());
		register.setSemester(exam.getSemester());
		register.setStatus("pending");
		
		try {
			t = session.beginTransaction();

			session.save(register);

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
	
	@RequestMapping(value="updateRegistration", method=RequestMethod.PUT)
	public void acceptRegistration(@RequestParam("id") String id) {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try {
			t = session.beginTransaction();

			Query query = session.createQuery("update ExamRegister set status='accept' where id =" + id);

			query.executeUpdate();
			
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
	
	@RequestMapping(value="/allRegistrations", method=RequestMethod.GET)
	public List<ExamRegister> getAllRegistrations(){
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<ExamRegister> list = new ArrayList<ExamRegister>();

		try {
			t = session.beginTransaction();

			list = session.createQuery("from ExamRegister").list();
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
	
	@RequestMapping(value="/registrations", method=RequestMethod.GET)
	public List<ExamRegister> getExamDetails(@RequestParam("stuId") String stuId) {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<ExamRegister> list = new ArrayList<ExamRegister>();

		try {
			t = session.beginTransaction();

			list = session.createQuery("from ExamRegister where stu_id='" + stuId + "'").list();
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
