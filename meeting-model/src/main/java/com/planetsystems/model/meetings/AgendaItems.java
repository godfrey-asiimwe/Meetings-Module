package com.planetsystems.model.meetings;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;




@Entity
@Table(name="AgendaItems")
public class AgendaItems implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1205479760041490395L;
	private String id;
	private Date time;
	private String action;
	private String rank;
	private String comment;
	private Agenda agenda;
	
	public AgendaItems() {
		super();
	}

	public AgendaItems(String action, String rank,
			String comment, Date time,List<Agenda> agendas) {
		
		super();
		this.action = action;
		this.rank = rank;
		this.comment = comment;
		this.time=time;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	
	@ManyToOne
	public Agenda getAgenda() {
		return agenda;
	}
	
	public void setAgenda(Agenda agenda) {
		this.agenda=agenda;
	}
	
}
