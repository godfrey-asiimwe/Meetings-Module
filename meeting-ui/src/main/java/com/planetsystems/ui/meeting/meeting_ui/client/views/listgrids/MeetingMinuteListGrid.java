/**
 * 
 */
package com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids;


import java.util.List;
import com.planetsystems.model.meetings.CommitteeMember;
import com.planetsystems.model.meetings.Minutes;
import com.planetsystems.ui.utils.utils_ui.client.widgets.SuperListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author Planet Dev005
 *
 */
public class MeetingMinuteListGrid extends SuperListGrid{

	public static String MINUTE_ID="id";
	public static String MEETING_ID="meetingid";
	public static String MEETING_NAME="meeting";
	public static String ITEM_ID="itemid";
	public static String ITEM_NAME="item";
	public static String ACTION="action";
	public static String PERSON="person";
	public static String COMMENT="comment";
	public static String DEADLINE="Deadline";
	public static String DATE_CREAATION="dateofCreation";
	public static String USER_STATUS="Status";
	
	
	/**
	 * constructor
	 */
	public MeetingMinuteListGrid() {
		  super();
		  
		  ListGridField id = new ListGridField(MINUTE_ID, "Id");
			id.setHidden(true);
			
		  ListGridField meetingId=new ListGridField(MEETING_ID,"Meeting Id");
		  meetingId.setHidden(true);
		  
		  ListGridField itemId=new ListGridField(ITEM_ID,"ITEM Id");
		  itemId.setHidden(true);
			
		  ListGridField action=new ListGridField(ACTION,"Action");
		  ListGridField person=new ListGridField(PERSON,"Person");
		  ListGridField comment=new ListGridField(COMMENT,"Comment");
		  ListGridField deadline=new ListGridField(DEADLINE,"Deadline");
		  ListGridField dateofCreation=new ListGridField(DATE_CREAATION,"Date Creation");
		  ListGridField userStatus=new ListGridField(USER_STATUS,"Status");
		  
		  
		  this.setFields(id,itemId,action,person,deadline,comment,dateofCreation,userStatus);
	}
	
	public ListGridRecord addRowData(Minutes minute) {
		ListGridRecord record = new ListGridRecord();
		
		record.setAttribute(MINUTE_ID,minute.getId());
		record.setAttribute(ACTION, minute.getAction());
		record.setAttribute(PERSON, minute.getPersonResponsible());
		record.setAttribute(COMMENT,minute.getComment());
		record.setAttribute(DEADLINE,minute.getDeadLine());
		record.setAttribute(DATE_CREAATION, minute.getDateMade());
		
		return record;
	}

	public void addRecordsToGrid(List<Minutes> minutes) {
		ListGridRecord[] records = new ListGridRecord[minutes.size()];
		int row = 0;
		for (Minutes item : minutes) {
			records[row] = addRowData(item);
			row++;
		}
		this.setData(records);

	}
	

}
