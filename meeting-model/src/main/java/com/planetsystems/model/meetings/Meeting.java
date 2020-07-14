package com.planetsystems.model.meetings;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.planetsystems.model.utils.utils_model.Account;
import com.planetsystems.model.utils.utils_model.Activity;
import com.planetsystems.model.utils.utils_model.FinancialYear;
import com.planetsystems.model.utils.utils_model.FundingAgency;
import com.planetsystems.model.utils.utils_model.LineItem;
import com.planetsystems.model.utils.utils_model.ProcuringEntity;
import com.planetsystems.model.utils.utils_model.Status;
import com.planetsystems.model.utils.utils_model.User;

@Entity
@Table(name="Meeting")
public class Meeting implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String procurementReferenceNumber;
	private FinancialYear financialYear;
	private User user;
	private Date dateMade;
	private ProcuringEntity procuringEntity;
	private Account account;
	private Activity activity;
	private LineItem lineItem;
	private Status status = Status.ACTIVE;
	private String comment;
	private Float totalAmount;
	private FundingAgency fundingAgency;
	
	public Meeting(){
		
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
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	public FinancialYear getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(columnDefinition = "TEXT")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	

	public Date getDateMade() {
		return dateMade;
	}

	public void setDateMade(Date dateMade) {
		this.dateMade = dateMade;
	}
	
	public String getProcurementReferenceNumber() {
		return procurementReferenceNumber;
	}

	public void setProcurementReferenceNumber(String procurementReferenceNumber) {
		this.procurementReferenceNumber = procurementReferenceNumber;
	}




	/**
	 * @return the fundingAgency
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	public FundingAgency getFundingAgency() {
		return fundingAgency;
	}


	/**
	 * @param fundingAgency the fundingAgency to set
	 */
	public void setFundingAgency(FundingAgency fundingAgency) {
		this.fundingAgency = fundingAgency;
	}


	/**
	 * @return the procuringEntity
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	public ProcuringEntity getProcuringEntity() {
		return procuringEntity;
	}


	/**
	 * @param procuringEntity the procuringEntity to set
	 */
	public void setProcuringEntity(ProcuringEntity procuringEntity) {
		this.procuringEntity = procuringEntity;
	}


	/**
	 * @return the account
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	public Account getAccount() {
		return account;
	}


	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}


	/**
	 * @return the activity
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	public Activity getActivity() {
		return activity;
	}


	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * @return the lineItem
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	public LineItem getLineItem() {
		return lineItem;
	}


	/**
	 * @param lineItem the lineItem to set
	 */
	public void setLineItem(LineItem lineItem) {
		this.lineItem = lineItem;
	}


	/**
	 * @return the totalAmount
	 */
	public Float getTotalAmount() {
		return totalAmount;
	}


	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}


}
