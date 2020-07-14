/**
 * 
 */
package com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids;


import java.util.List;

import com.planetsystems.model.meetings.Agenda;
import com.planetsystems.ui.utils.utils_ui.client.widgets.DeleteButton;
import com.planetsystems.ui.utils.utils_ui.client.widgets.SaveButton;
import com.planetsystems.ui.utils.utils_ui.client.widgets.SuperListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Planet Dev005
 *
 */

public class AgendaListGrid extends SuperListGrid{

	public static String AGENDA_ID="id";
	public static String AGENDA_NAME="Name";
	public static String AGENDA_NO="NO.";
	public static String AGENDA_DESC="DSC";
	
	public static String AGENDA_DATE_CREATION="date_creation";
	public static AgendaItemListGrid agendaItemListGrid;
	
	public SaveButton saveButton;
	
	public DeleteButton deleteButton;
	
	/**
	 * constructor
	 */
	public AgendaListGrid() {
		  super();
		  

	     deleteButton=new DeleteButton("Delete Button");
		  
		  ListGridField id = new ListGridField(AGENDA_ID, "Id");
			id.setHidden(true);
			
		  ListGridField name=new ListGridField(AGENDA_NAME,"Name");
		  
		  
		  ListGridField agendaNo=new ListGridField(AGENDA_NO,"NO.");
		  
		  ListGridField desc=new ListGridField(AGENDA_DESC,"DSC");
		  
		  ListGridField Date=new ListGridField(AGENDA_DATE_CREATION,"Date of Creation");
		  
		  this.setFields(id,agendaNo,name,desc,Date);
			this.setCanExpandRecords(true);
	}
	
	protected Canvas getExpansionComponent(final ListGridRecord record) {
		// rollOverRecord = this.getRecord(rowNum);
		final ListGrid grid = this;

		for (ListGridRecord gr : grid.getRecords()) {
			if (gr != record) {
				grid.collapseRecord(gr);
			}
		}
		
		grid.selectRecord(record);

		VLayout layout = new VLayout(5);
		layout.setPadding(5);
		
		agendaItemListGrid=new AgendaItemListGrid();

		agendaItemListGrid.setWidth100();
		agendaItemListGrid.setHeight(224);
		agendaItemListGrid.setCellHeight(22);

		layout.addMember(agendaItemListGrid);

		saveButton=new SaveButton("Save Button");
		deleteButton=new DeleteButton("Delete Button");
		
        DynamicForm loadButtonform = new DynamicForm();
        loadButtonform.setFields(deleteButton,saveButton);
		loadButtonform.setAutoWidth();
		
		IButton closeButton = new IButton("Close");
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				grid.collapseRecord(record);
			}
		});
		
		HLayout hLayout = new HLayout(10);
		hLayout.setAlign(Alignment.CENTER);
		hLayout.addMember(closeButton);

		layout.addMember(hLayout);

		return layout;

	}

	public ListGridRecord addRowData(Agenda agenda) {
		
		ListGridRecord record = new ListGridRecord();

		record.setAttribute(AGENDA_ID, agenda.getId());

		record.setAttribute(AGENDA_NO, agenda.getAgendaNo());
		
		record.setAttribute(AGENDA_NAME, agenda.getName());

		record.setAttribute(AGENDA_DESC, agenda.getDescription());
		
		record.setAttribute(AGENDA_DATE_CREATION, agenda.getDateOfCreation());

		return record;
		
	}

	public void addRecordsToGrid(List<Agenda> agendas) {
		ListGridRecord[] records = new ListGridRecord[agendas.size()];
		int row = 0;
		for (Agenda item : agendas) {
			records[row] = addRowData(item);
			row++;
		}
		this.setData(records);

	}
	
	public AgendaItemListGrid getAgendaItemListGrid() {
		return agendaItemListGrid;
	}

	public void setAgendaItemListGrid(AgendaItemListGrid agendaItemListGrid) {
		AgendaListGrid.agendaItemListGrid = agendaItemListGrid;
	}
	
	public SaveButton getSaveButton() {
		return saveButton;
	}
	
	public DeleteButton getDeleteButton() {
		return deleteButton;
	}

}
