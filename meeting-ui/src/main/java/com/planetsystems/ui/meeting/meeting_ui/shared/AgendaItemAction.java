package com.planetsystems.ui.meeting.meeting_ui.shared;

import java.util.List;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;
import com.planetsystems.model.meetings.AgendaItems;
import com.planetsystems.model.meetings.CommitteeMember;

public class AgendaItemAction extends UnsecuredActionImpl<AgendaItemActionResult> {

	private String operation;
	private AgendaItems agendaItems;
	private List<AgendaItems> agendaItemList;
	
	private CommitteeMember committeeMember;
	private List<CommitteeMember> committeeMemberList;

	public AgendaItemAction() {

	}

	
	public AgendaItemAction(String operation, AgendaItems agendaItems) {
		this.operation=operation;
		this.agendaItems=agendaItems;
	}
	
	public AgendaItemAction(CommitteeMember committeeMember,String operation) {
		this.operation=operation;
		this.committeeMember=committeeMember;
	}
	
	public AgendaItemAction(List<CommitteeMember> committeeMemberList,String operation) {
		this.operation=operation;
		this.committeeMemberList=committeeMemberList;
	}
	
	public AgendaItemAction(String operation, List<AgendaItems> agendaItemList) {
		this.operation=operation;
		this.setAgendaItemList(agendaItemList);
	}

	
	public AgendaItemAction(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

	/**
	 * @return the agendaItems
	 */
	public AgendaItems getAgendaItems() {
		return agendaItems;
	}

	/**
	 * @param agendaItems the agendaItems to set
	 */
	public void setAgendaItems(AgendaItems agendaItems) {
		this.agendaItems = agendaItems;
	}

	/**
	 * @return the agendaItemList
	 */
	public List<AgendaItems> getAgendaItemList() {
		return agendaItemList;
	}

	/**
	 * @param agendaItemList the agendaItemList to set
	 */
	public void setAgendaItemList(List<AgendaItems> agendaItemList) {
		this.agendaItemList = agendaItemList;
	}


	/**
	 * @return the committeeMember
	 */
	public CommitteeMember getCommitteeMember() {
		return committeeMember;
	}


	/**
	 * @param committeeMember the committeeMember to set
	 */
	public void setCommitteeMember(CommitteeMember committeeMember) {
		this.committeeMember = committeeMember;
	}


	/**
	 * @return the committeeMemberList
	 */
	public List<CommitteeMember> getCommitteeMemberList() {
		return committeeMemberList;
	}


	/**
	 * @param committeeMemberList the committeeMemberList to set
	 */
	public void setCommitteeMemberList(List<CommitteeMember> committeeMemberList) {
		this.committeeMemberList = committeeMemberList;
	}


}
