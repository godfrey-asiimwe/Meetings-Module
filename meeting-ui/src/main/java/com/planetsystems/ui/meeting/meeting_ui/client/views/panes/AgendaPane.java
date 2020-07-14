package com.planetsystems.ui.meeting.meeting_ui.client.views.panes;

import com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids.AgendaListGrid;
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

public class AgendaPane extends VLayout{

	private AgendaListGrid agendaListGrid;

	private LoadButton loadAgendas;
	private SaveButton addNewbutton;
	private DeleteButton deleteAgendas;
	private DateField fromDate;
	private DateField toDate;
	private ComboBox status;

	private ControlsPane controlsPane;

	public AgendaPane() {
		super();

		Label header = new Label();
		header = new Label();
		header.setStyleName("crm-ContextArea-Header-Label");
		header.setContents("Agenda");
		header.setWidth("100%");
		header.setAutoHeight();
		header.setMargin(10);
		header.setAlign(Alignment.LEFT);

		controlsPane = new ControlsPane();

		agendaListGrid = new AgendaListGrid();

		fromDate = new DateField();
		fromDate.setTitle("From");

		toDate = new DateField();
		toDate.setTitle("To");

		status = new ComboBox();
		status.setTitle("Status");


		addNewbutton = new SaveButton("New");
		deleteAgendas=new DeleteButton("Delete");
		loadAgendas=new LoadButton("Agenda");

		DynamicForm loadAgendaButtonform = new DynamicForm();
		loadAgendaButtonform.setFields(loadAgendas);
		loadAgendaButtonform.setAutoWidth();

		DynamicForm addNewbuttonform = new DynamicForm();
		addNewbuttonform.setFields(addNewbutton);
		addNewbuttonform.setAutoWidth();
		
		DynamicForm deleteAgendaButtonForm = new DynamicForm();
		deleteAgendaButtonForm.setFields(deleteAgendas);
		deleteAgendaButtonForm.setAutoWidth();

		HLayout hLayout = new HLayout();
		hLayout.setMembers(addNewbuttonform);
		hLayout.setAutoHeight();
		hLayout.setMargin(1);

		VLayout layout = new VLayout();
		layout.addMember(controlsPane);
		layout.addMember(header);
		layout.addMember(hLayout);
		layout.addMember(agendaListGrid);

		this.addMember(layout);

	}
	
	public AgendaListGrid getAgendaListGrid() {
		return agendaListGrid;
	}


	public SaveButton getAddNewbutton() {
		return addNewbutton;
	}
	
	public DeleteButton deleteAgenda() {
		return deleteAgendas;
	}

	public LoadButton loadcommittees() {
		return loadAgendas;
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
