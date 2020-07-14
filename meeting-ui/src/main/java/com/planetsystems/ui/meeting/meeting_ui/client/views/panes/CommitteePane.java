package com.planetsystems.ui.meeting.meeting_ui.client.views.panes;

import com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids.CommitteeListGrid;
import com.planetsystems.ui.utils.utils_ui.client.widgets.ComboBox;
import com.planetsystems.ui.utils.utils_ui.client.widgets.ControlsPane;
import com.planetsystems.ui.utils.utils_ui.client.widgets.DateField;
import com.planetsystems.ui.utils.utils_ui.client.widgets.DeleteButton;
import com.planetsystems.ui.utils.utils_ui.client.widgets.LoadButton;
import com.planetsystems.ui.utils.utils_ui.client.widgets.SaveButton;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class CommitteePane extends VLayout{

	private CommitteeListGrid committeeListGrid;

	private LoadButton loadcommittees;
	private SaveButton addNewbutton;
	private DeleteButton deletecommittes;
	private DateField fromDate;
	private DateField toDate;
	private ComboBox status;

	private ControlsPane controlsPane;

	public CommitteePane() {
		super();

		Label header = new Label();
		header = new Label();
		header.setStyleName("crm-ContextArea-Header-Label");
		header.setContents("Committees");
		header.setWidth("100%");
		header.setAutoHeight();
		header.setMargin(10);
		header.setAlign(Alignment.LEFT);

		controlsPane = new ControlsPane();

		committeeListGrid = new CommitteeListGrid();

		fromDate = new DateField();
		fromDate.setTitle("From");

		toDate = new DateField();
		toDate.setTitle("To");

		status = new ComboBox();
		status.setTitle("Status");


		addNewbutton = new SaveButton("New");
		deletecommittes=new DeleteButton("Delete");
		loadcommittees=new LoadButton("Load Committees");

		DynamicForm loadCommiteesButtonform = new DynamicForm();
		loadCommiteesButtonform.setFields(loadcommittees);
		loadCommiteesButtonform.setAutoWidth();

		DynamicForm addNewbuttonform = new DynamicForm();
		addNewbuttonform.setFields(addNewbutton);
		addNewbuttonform.setAutoWidth();
		
		DynamicForm deletecommittesButtonForm = new DynamicForm();
		deletecommittesButtonForm.setFields(deletecommittes);
		deletecommittesButtonForm.setAutoWidth();

		HLayout hLayout = new HLayout();
		hLayout.setMembers(loadCommiteesButtonform,addNewbuttonform,deletecommittesButtonForm);
		hLayout.setAutoHeight();
		hLayout.setMargin(1);

		VLayout layout = new VLayout();
		layout.addMember(controlsPane);
		layout.addMember(header);
		layout.addMember(hLayout);
		layout.addMember(committeeListGrid);

		this.addMember(layout);

	}
	
	public CommitteeListGrid getCommitteeListGrid() {
		return committeeListGrid;
	}


	public SaveButton getAddNewbutton() {
		return addNewbutton;
	}
	
	public DeleteButton deletecommittes() {
		return deletecommittes;
	}

	public LoadButton loadcommittees() {
		return loadcommittees;
	}

	public DateField getFromDate() {
		return fromDate;
	}

	public DateField getToDate() {
		return toDate;
	}

	public ComboBox getStatus() {
		return status;
	}

	public ControlsPane getControlsPane() {
		return controlsPane;
	}
}
