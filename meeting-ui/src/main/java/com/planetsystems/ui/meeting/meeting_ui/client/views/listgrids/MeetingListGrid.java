/**
 * 
 */
package com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids;


import java.util.List;
import com.google.gwt.i18n.client.NumberFormat;
import com.planetsystems.model.meetings.Meetings;
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

public class MeetingListGrid extends SuperListGrid{

	public static String MEETINGS_ID = "id";
	public static String REF_NO = "procurementReferenceNumber";
	
	public static String FINANCIAL_YEAR_ID = "financialYearId";
	
	public static String AGENDA_ID="agendaId";
	public static String AGENDA_NAME="agendaName";
	
	public static String COMMITTE_ID="committeeId";
	public static String COMMITTE_NAME="committee";
	
	public static String FINANCIAL_YEAR = "financialYear";

	public static String TITTLE = "tittle";
	
	public static String VENUE = "venue";
	
	public static String STATUS = "status";
	public static String COMMENT = "comment";

	public static String DATE_MADE = "dateMade";

	public static String APPROVAL_STATUS = "status";


	NumberFormat nf = NumberFormat.getFormat("#,##0.00");
	
	
	public static AgendaItemListGrid agendaItemListGrid;
	
	public static CommitteUserListGrid committeeMemberListGrid;
	
	public static MeetingMinuteListGrid meetingMinuteListGrid;
	
	/**
	 * constructor
	 */
	public MeetingListGrid() {
		  super();
		  

		  ListGridField id = new ListGridField(MEETINGS_ID, "Id");
			id.setHidden(true);

			ListGridField procurementReferenceNumber = new ListGridField(REF_NO, "Reference Number");

			ListGridField venue = new ListGridField(VENUE, "Venue");

			ListGridField tittle = new ListGridField(TITTLE, "tittle");

			ListGridField status = new ListGridField(STATUS, "Status");

			ListGridField dateMade = new ListGridField(DATE_MADE, "Meeting Date");

			ListGridField comment = new ListGridField(COMMENT, "Comment");

			
			ListGridField financialYearId = new ListGridField(FINANCIAL_YEAR_ID,
			  "Financial Year  Id"); financialYearId.setHidden(true);
			  
			  
			ListGridField financialYear = new ListGridField(FINANCIAL_YEAR,
				"Financail Year"); financialYear.setHidden(true); 
			
			ListGridField agendaId=new ListGridField(AGENDA_ID,"Agenda Id");
			agendaId.setHidden(true);
			
			ListGridField agendaname=new ListGridField(AGENDA_NAME,"Agenda");
			
			ListGridField committeeId=new ListGridField(COMMITTE_ID,"Committee Id");
			committeeId.setHidden(true);
			ListGridField committee=new ListGridField(COMMITTE_NAME,"Committee");

		this.setFields(id, financialYearId,financialYear,agendaId, committeeId,procurementReferenceNumber,tittle,agendaname,committee, venue, dateMade,
				comment/* , status */);
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
		
		committeeMemberListGrid=new CommitteUserListGrid();
		
		meetingMinuteListGrid=new MeetingMinuteListGrid();

		agendaItemListGrid.setWidth100();
		agendaItemListGrid.setHeight(224);
		agendaItemListGrid.setCellHeight(22);
		
		committeeMemberListGrid.setWidth100();
		committeeMemberListGrid.setHeight(224);
		committeeMemberListGrid.setCellHeight(22);
		
		meetingMinuteListGrid.setWidth100();
		meetingMinuteListGrid.setHeight(224);
		meetingMinuteListGrid.setCellHeight(22);

		layout.addMember(agendaItemListGrid);
		layout.addMember(committeeMemberListGrid);
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
	


public ListGridRecord addRowData(Meetings meeting) {
		
		ListGridRecord record = new ListGridRecord();

		record.setAttribute(MEETINGS_ID, meeting.getId());

		record.setAttribute(REF_NO, meeting.getProcurementRefNo());

		record.setAttribute(TITTLE, meeting.getTitle());

		record.setAttribute(VENUE, meeting.getVenue());
		
		record.setAttribute(DATE_MADE, meeting.getDate());

		record.setAttribute(COMMENT, meeting.getComment());

		

		 record.setAttribute(FINANCIAL_YEAR_ID, meeting.getFinancialYear().getId());
		  
		 record.setAttribute(FINANCIAL_YEAR, meeting.getFinancialYear().getName());
		 
		 record.setAttribute(AGENDA_ID, meeting.getAgenda().getId());
		 record.setAttribute(AGENDA_NAME, meeting.getAgenda().getName());

		 
		 record.setAttribute(COMMITTE_ID, meeting.getCommittee().getId());
		 record.setAttribute(COMMITTE_NAME, meeting.getCommittee().getName());

		
		/*
		 * if (meeting.getStatus() != null) { record.setAttribute(APPROVAL_STATUS,
		 * meeting.getStatus()); }
		 */

		return record;
		
	}

	public void addRecordsToGrid(List<Meetings> meetings) {
		ListGridRecord[] records = new ListGridRecord[meetings.size()];
		int row = 0;
		for (Meetings item : meetings) {
			records[row] = addRowData(item);
			row++;
		}
		this.setData(records);

	}
	
	public static AgendaItemListGrid getAgendaItemListGrid() {
		return agendaItemListGrid;
	}

	public static void setAgendaItemListGrid(AgendaItemListGrid agendaListGrid) {
		MeetingListGrid.agendaItemListGrid = agendaListGrid;
	}
	
	public static CommitteUserListGrid getCommitteMemberListGrid() {
		return committeeMemberListGrid;
	}

	public static void setCommitteMemberListGrid(CommitteUserListGrid committeeMemberListGrid) {
		MeetingListGrid.committeeMemberListGrid = committeeMemberListGrid;
	}
	
	public static MeetingMinuteListGrid getMeetingMinuteListGrid() {
		return meetingMinuteListGrid;
	}

	public static void setMeetingMinuteListGrid(MeetingMinuteListGrid meetingMinuteListGrid) {
		MeetingListGrid.meetingMinuteListGrid = meetingMinuteListGrid;
	}


}
