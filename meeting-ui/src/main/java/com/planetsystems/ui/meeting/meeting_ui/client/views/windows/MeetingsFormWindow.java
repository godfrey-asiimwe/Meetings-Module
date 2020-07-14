package com.planetsystems.ui.meeting.meeting_ui.client.views.windows;

/*
 * This form will be used to implement all the practices in government procurement governed by PPDA
 * i.e the entity organization using this system will have to identify its self as either as private or government entity
 * 
 * */

import com.planetsystems.ui.utils.utils_ui.client.widgets.ComboBox;
import com.planetsystems.ui.utils.utils_ui.client.widgets.CommentField;
import com.planetsystems.ui.utils.utils_ui.client.widgets.SaveButton;
import com.planetsystems.ui.utils.utils_ui.client.widgets.TextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;

public class MeetingsFormWindow extends Window {

	private TextField name;
	private TextField venue;
	private CommentField comment;
	
	private ComboBox committeeCombo;
	private ComboBox agendaCombo;
	private ComboBox financialYearCombo;

	private SaveButton saveCommitteButton;

	private Label heading;

	public MeetingsFormWindow() {
		super();
		
		heading = new Label();
		heading.setStyleName("crm-ContextArea-Header-Label");
		heading.setContents("Meeting Information");
		heading.setWidth("100%");
		heading.setAutoHeight();
		heading.setMargin(10);
		heading.setAlign(Alignment.LEFT);


		comment = new CommentField("shortDescription", "Short Description");


		saveCommitteButton = new SaveButton("Save");

		name = new TextField();
		name.setTitle("Title");
		
		venue = new TextField();
		venue.setTitle("Venue");
		
		committeeCombo=new ComboBox();
		committeeCombo.setTitle("Committee");
		
		agendaCombo=new ComboBox();
		agendaCombo.setTitle("Agenda");
		
		financialYearCombo=new ComboBox();
		financialYearCombo.setTitle("Financial Year");

		DynamicForm form = new DynamicForm();
		form.setNumCols(4);
		form.setPadding(9); 
		form.setFields(name, venue, comment,committeeCombo,agendaCombo,financialYearCombo);
		form.setTitleAlign(Alignment.CENTER);


		/* BID BUTTONS */
		DynamicForm savebidButtonform = new DynamicForm();
		savebidButtonform.setFields(saveCommitteButton);

		/* END BID BUTTONS */


		VLayout requisitionbuttonHolder = new VLayout();

		requisitionbuttonHolder.setMembers(savebidButtonform);
		requisitionbuttonHolder.setWidth100();
		requisitionbuttonHolder.setAutoHeight();

		VLayout requisitionPart1Layout = new VLayout();
		requisitionPart1Layout.addMember(heading);
		requisitionPart1Layout.addMember(form);
		requisitionPart1Layout.addMember(requisitionbuttonHolder);
		requisitionPart1Layout.setMargin(5); 

		this.addItem(requisitionPart1Layout);
		this.setWidth("60%");
		this.setHeight("40%");
		this.setAutoCenter(true);
		this.setTitle("New Committee");
		this.setIsModal(true);
		this.setShowModalMask(true);
	}

	public SaveButton getSaveCommitteeButton() {
		return saveCommitteButton;
	}
	

	public Label getHeading() {
		return heading;
	}

	public CommentField getComment() {
		return comment;
	}
	
	public TextField getName() {
		return name;
	}


	/**
	 * @return the venue
	 */
	public TextField getVenue() {
		return venue;
	}

	/**
	 * @param venue the venue to set
	 */
	public void setVenue(TextField venue) {
		this.venue = venue;
	}

	/**
	 * @return the committeeCombo
	 */
	public ComboBox getCommitteeCombo() {
		return committeeCombo;
	}

	/**
	 * @param committeeCombo the committeeCombo to set
	 */
	public void setCommitteeCombo(ComboBox committeeCombo) {
		this.committeeCombo = committeeCombo;
	}

	/**
	 * @return the agendaCombo
	 */
	public ComboBox getAgendaCombo() {
		return agendaCombo;
	}

	/**
	 * @param agendaCombo the agendaCombo to set
	 */
	public void setAgendaCombo(ComboBox agendaCombo) {
		this.agendaCombo = agendaCombo;
	}

	/**
	 * @return the financialYearCombo
	 */
	public ComboBox getFinancialYearCombo() {
		return financialYearCombo;
	}

	/**
	 * @param financialYearCombo the financialYearCombo to set
	 */
	public void setFinancialYearCombo(ComboBox financialYearCombo) {
		this.financialYearCombo = financialYearCombo;
	}



}
