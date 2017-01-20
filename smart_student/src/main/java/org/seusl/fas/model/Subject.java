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
@Table(name = "subject")
public class Subject {

	@Id
	@Column(name = "subjectId")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "teacher_teacherId")
	private String teacherId;

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
	public String getTeacherId() {
		return teacherId;
	}

	/**
	 * @param teacherId
	 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

}
