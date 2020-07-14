package com.planetsystems.model.meetings;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.planetsystems.model.utils.utils_model.Status;

@Entity
@Table(name="Minutes")
public class Minutes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Meetings meeting;
	private AgendaItems agendaItem;
	private String Action;
	private String personResponsible;
	private Date deadLine;
	private String comment;
	private Status status;
	private Date dateMade;

	public Minutes() {
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

	/**
	 * @return the agendaItem
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AgendaItem_ID")
	public AgendaItems getAgendaItem() {
		return agendaItem;
	}

	/**
	 * @param agendaItem the agendaItem to set
	 */
	public void setAgendaItem(AgendaItems agendaItem) {
		this.agendaItem = agendaItem;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return Action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		Action = action;
	}

	/**
	 * @return the personResponsible
	 */
	public String getPersonResponsible() {
		return personResponsible;
	}

	/**
	 * @param personResponsible the personResponsible to set
	 */
	public void setPersonResponsible(String personResponsible) {
		this.personResponsible = personResponsible;
	}

	/**
	 * @return the deadLine
	 */
	public Date getDeadLine() {
		return deadLine;
	}

	/**
	 * @param deadLine the deadLine to set
	 */
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the meeting
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Meeting_ID")
	public Meetings getMeeting() {
		return meeting;
	}

	/**
	 * @param meeting the meeting to set
	 */
	public void setMeeting(Meetings meeting) {
		this.meeting = meeting;
	}

	/**
	 * @return the dateMade
	 */
	public Date getDateMade() {
		return dateMade;
	}

	/**
	 * @param dateMade the dateMade to set
	 */
	public void setDateMade(Date dateMade) {
		this.dateMade = dateMade;
	}


}
