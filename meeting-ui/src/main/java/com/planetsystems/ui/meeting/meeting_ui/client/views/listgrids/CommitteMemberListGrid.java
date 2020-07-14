/**
 * 
 */
package com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids;

import java.util.List;

import com.google.gwt.i18n.client.NumberFormat;
import com.planetsystems.model.meetings.Meetings;
import com.planetsystems.ui.utils.utils_ui.client.widgets.SuperListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class CommitteMemberListGrid extends SuperListGrid {

	public static String MEETINGS_ID = "id";
	public static String REF_NO = "procurementReferenceNumber";
	
	public static String FINANCIAL_YEAR_ID = "financialYearId";
	
	public static String FINANCIAL_YEAR = "financialYear";

	public static String TITTLE = "tittle";
	
	public static String VENUE = "venue";
	
	public static String STATUS = "status";
	public static String COMMENT = "comment";

	public static String DATE_MADE = "dateMade";

	public static String APPROVAL_STATUS = "status";



	NumberFormat nf = NumberFormat.getFormat("#,##0.00");

	public CommitteMemberListGrid() {
		super();

		ListGridField id = new ListGridField(MEETINGS_ID, "Id");
		/* id.setHidden(true); */

		ListGridField procurementReferenceNumber = new ListGridField(REF_NO, "Reference Number");

		ListGridField venue = new ListGridField(VENUE, "Venue");

		ListGridField tittle = new ListGridField(TITTLE, "tittle");

		ListGridField status = new ListGridField(STATUS, "Status");

		ListGridField dateMade = new ListGridField(DATE_MADE, "Meeting Date");

		ListGridField comment = new ListGridField(COMMENT, "Comment");

		/*
		 * ListGridField financialYearId = new ListGridField(FINANCIAL_YEAR_ID,
		 * "Financial Year  Id"); financialYearId.setHidden(true);
		 * 
		 * ListGridField financialYear = new ListGridField(FINANCIAL_YEAR,
		 * "Financail Year"); financialYear.setHidden(true);
		 */

		

		this.setFields(id /* financialYearId,financialYear, */ /*procurementReferenceNumber,tittle,venue, dateMade,comment, status*/);
		this.setCanExpandRecords(true);

	}

	public ListGridRecord addRowData(Meetings meeting) {
		
		ListGridRecord record = new ListGridRecord();

		record.setAttribute(MEETINGS_ID, meeting.getId());

		/*
		 * record.setAttribute(REF_NO, meeting.getProcurementRefNo());
		 * 
		 * record.setAttribute(TITTLE, meeting.getTitle());
		 * 
		 * record.setAttribute(VENUE, meeting.getVenue());
		 * 
		 * record.setAttribute(DATE_MADE, meeting.getDate());
		 * 
		 * record.setAttribute(COMMENT, meeting.getComment());
		 */
		

		//record.setAttribute(FINANCIAL_YEAR_ID, meeting.getFinancialYear().getId());

		//record.setAttribute(FINANCIAL_YEAR, meeting.getFinancialYear().getName());
		
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


}
