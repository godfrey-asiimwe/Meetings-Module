/**
 * 
 */
package com.planetsystems.model.meetings;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.planetsystems.model.utils.utils_model.Status;
import com.planetsystems.model.utils.utils_model.User;

/**
 * @author Planet Dev005
 *
 */
@Entity
@Table(name="CommitteeMember")
public class CommitteeMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private User user;
	private String position;
	private Date dateMade;
	private Status status = Status.ACTIVE;
	private String comment;
	private Committees committee;
	
	public CommitteeMember() {
		super();
	}
	
	@Id
	@GeneratedValue(generator ="system-uuid")
	@GenericGenerator(name ="system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	@Transient
	public void setId(String id) {
		this.id = id;
	}
	
	@ManyToOne
	public User getUser() {
		return user;
	}

	@Transient
	public void setUser(User user) {
		this.user = user;
	}
	
	public Status getStatus() {
		return status;
	}

	@Transient
	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(columnDefinition = "TEXT")
	public String getComment() {
		return comment;
	}

	@Transient
	public void setComment(String comment) {
		this.comment = comment;
	}
	

	
	public Date getDateMade() {
		return dateMade;
	}

	@Transient
	public void setDateMade(Date dateMade) {
		this.dateMade = dateMade;
	}

	/**
	 * @return the position
	 */
	
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	@Transient
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the committee
	 */
	@ManyToOne
	public Committees getCommittee() {
		return committee;
	}

	/**
	 * @param committee the committee to set
	 */
	public void setCommittee(Committees committee) {
		this.committee = committee;
	}



}
