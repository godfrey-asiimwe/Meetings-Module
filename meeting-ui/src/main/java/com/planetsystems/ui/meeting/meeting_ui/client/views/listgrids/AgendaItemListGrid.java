/**
 * 
 */
package com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids;


import java.util.List;

import com.planetsystems.model.meetings.AgendaItems;
import com.planetsystems.ui.utils.utils_ui.client.widgets.SuperListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Planet Dev005
 *
 */
public class AgendaItemListGrid extends SuperListGrid{

	public static String AGENDA_ITEM_ID="id";
	public static String AGENDA_ITEM_RANK="Item_Rank";
	public static String AGENDA_ITEM_ACTION="Item_Action";
	public static String AGENDA_ITEM_COMMENT="Item_comment";
	public static String AGENDA_ITEM_DATE_CREATION="date_creation";
	public static MeetingMinuteListGrid meetingMinuteListGrid;

	/**
	 * constructor
	 */
	public AgendaItemListGrid() {
		  super();
		  
		  ListGridField id = new ListGridField(AGENDA_ITEM_ID, "Id");
			id.setHidden(true);
			
		  ListGridField rank=new ListGridField(AGENDA_ITEM_RANK,"Rank");
		  
		  
		  ListGridField action=new ListGridField(AGENDA_ITEM_ACTION,"Action");
		  
		  ListGridField Comment=new ListGridField(AGENDA_ITEM_COMMENT,"Comment");
		  
		  ListGridField Date=new ListGridField(AGENDA_ITEM_DATE_CREATION,"Date of Creation");
		  
		  this.setFields(id,action,rank,Comment,Date);
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
		
		meetingMinuteListGrid=new MeetingMinuteListGrid();
		
		meetingMinuteListGrid.setWidth100();
		meetingMinuteListGrid.setHeight(224);
		meetingMinuteListGrid.setCellHeight(22);

		layout.addMember(meetingMinuteListGrid);
		
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
	
	public ListGridRecord addRowData(AgendaItems item) {
		ListGridRecord record = new ListGridRecord();

		record.setAttribute(AGENDA_ITEM_ID, item.getId());

		record.setAttribute(AGENDA_ITEM_RANK, item.getRank());

		record.setAttribute(AGENDA_ITEM_ACTION, item.getAction());

		record.setAttribute(AGENDA_ITEM_COMMENT, item.getComment());
		
		record.setAttribute(AGENDA_ITEM_DATE_CREATION, item.getTime());

		return record;
	}

	public void addRecordsToGrid(List<AgendaItems> agendaItems) {
		ListGridRecord[] records = new ListGridRecord[agendaItems.size()];
		int row = 0;
		for (AgendaItems item : agendaItems) {
			records[row] = addRowData(item);
			row++;
		}
		this.setData(records);

	}
	
	
	public static MeetingMinuteListGrid getMeetingMinuteListGrid() {
		return meetingMinuteListGrid;
	}

	public static void setMeetingMinuteListGrid(MeetingMinuteListGrid meetingMinuteListGrid) {
		AgendaItemListGrid.meetingMinuteListGrid = meetingMinuteListGrid;
	}



}
