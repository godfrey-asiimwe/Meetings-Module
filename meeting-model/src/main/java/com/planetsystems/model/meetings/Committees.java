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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.planetsystems.model.utils.utils_model.Status;

/**
 * @author Planet Dev005
 *
 */
@Entity
@Table(name="Committees")
public class Committees implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String purpose;
	private Date dateMade;
	private Status status = Status.ACTIVE;
	private String comment;
	
	public Committees() {
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

	@Column(columnDefinition = "TEXT")
	public String getComment() {
		return comment;
	}

	@Transient
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
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
	


}
