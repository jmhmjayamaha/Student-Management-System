package org.seusl.fas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Dulari Ranaweera
 *
 */
@Entity
@Table(name="student")
public class Student {

	@Id
	@Column(name="stuId")
	private String id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="telNo")
	private String telNo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="acedemicYear")
	private String acedemicYear;

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return
	 */
	public String getTelNo() {
		return telNo;
	}

	/**
	 * @param telNo
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return
	 */
	public String getAcedemicYear() {
		return acedemicYear;
	}

	/**
	 * @param acedemicYear
	 */
	public void setAcedemicYear(String acedemicYear) {
		this.acedemicYear = acedemicYear;
	}

}
