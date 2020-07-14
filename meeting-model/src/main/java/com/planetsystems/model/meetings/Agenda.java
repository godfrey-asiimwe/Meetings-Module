package com.planetsystems.model.meetings;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Agenda")
public class Agenda implements Serializable{

	private static final long serialVersionUID = -5506891088717624287L;
	private String id;
	private String name;
	private String agendaNo;
	private String description;
	private Date dateOfCreation;
	
	public Agenda() {
		super();
	}
	
	@Id
	@GeneratedValue(generator ="system-uuid")
	@GenericGenerator(name ="system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getAgendaNo() {
		return agendaNo;
	}

	public void setAgendaNo(String agendaNo) {
		this.agendaNo = agendaNo;
	}

	/**
	 * @return the dateOfCreation
	 */
	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	/**
	 * @param dateOfCreation the dateOfCreation to set
	 */
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
}
