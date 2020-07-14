package com.planetsystems.model.meetings;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.planetsystems.model.utils.utils_model.FinancialYear;
import com.planetsystems.model.utils.utils_model.Status;

@Entity
@Table(name="Meetings")
@XmlRootElement
public class Meetings implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private Date date;
	private Committees committee;
	private Agenda agenda;
	private String title;
	private String venue;
	private Status status;
	private String procurementRefNo;
	private FinancialYear financialYear;
	private String comment;

	public Meetings() {
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Committee_ID")
	public Committees getCommittee() {
		return committee;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Agenda_ID")
	public Agenda  getAgenda() {
		return agenda;
	}
	
	/**
	 * @return the financialYear
	 */
	@ManyToOne
	@JoinColumn(name ="financialYear")
	public FinancialYear getFinancialYear() {
		return financialYear;
	}

	public void setCommittee(Committees committee) {
		this.committee = committee;
	}
	
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setProcurementRefNo(String procurementRefNo) {
		this.procurementRefNo = procurementRefNo;
	}

	@Column(unique=true)
	public String getProcurementRefNo() {
		return procurementRefNo;
	}
	
	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}

	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
