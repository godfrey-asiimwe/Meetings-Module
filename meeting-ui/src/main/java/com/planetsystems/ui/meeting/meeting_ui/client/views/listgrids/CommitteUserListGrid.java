/**
 * 
 */
package com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids;


import java.util.List;
import com.planetsystems.model.meetings.CommitteeMember;
import com.planetsystems.ui.utils.utils_ui.client.widgets.SuperListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author Planet Dev005
 *
 */
public class CommitteUserListGrid extends SuperListGrid{

	public static String MEMBER_ID="id";
	public static String USER_ID="userid";
	public static String USER_NAME="username";
	public static String POSITION="position";
	public static String COMMENT="comment";
	public static String COMMITTE_ID="committeeId";
	public static String DATE_CREAATION="dateofCreation";
	public static String USER_STATUS="Status";
	
	
	/**
	 * constructor
	 */
	public CommitteUserListGrid() {
		  super();
		  
		  ListGridField id = new ListGridField(MEMBER_ID, "Id");
			id.setHidden(true);
			
		  ListGridField userId=new ListGridField(USER_ID,"User Id");
		  userId.setHidden(true);
			
		  ListGridField username=new ListGridField(USER_NAME,"Name");
		  ListGridField position=new ListGridField(POSITION,"Position");
		  ListGridField comment=new ListGridField(COMMENT,"Comment");
		  
		  ListGridField committeeId=new ListGridField(COMMITTE_ID,"Committee Id");
		  committeeId.setHidden(true);
		  
		  ListGridField dateofCreation=new ListGridField(DATE_CREAATION,"Date Creation");
		  committeeId.setHidden(true);
		  ListGridField userStatus=new ListGridField(USER_STATUS,"Status");
		  
		  
		  this.setFields(id,userId,committeeId,username,position,comment,dateofCreation,userStatus);
	}
	
	public ListGridRecord addRowData(CommitteeMember committeeMember) {
		ListGridRecord record = new ListGridRecord();
		
		record.setAttribute(MEMBER_ID,committeeMember.getId());
		record.setAttribute(USER_ID, committeeMember.getUser().getId());

		record.setAttribute(USER_NAME, committeeMember.getUser().getUsername());
		record.setAttribute(POSITION, committeeMember.getPosition());
		record.setAttribute(COMMENT, committeeMember.getComment());
		record.setAttribute(COMMITTE_ID, committeeMember.getCommittee().getId());
		record.setAttribute(DATE_CREAATION, committeeMember.getDateMade());
		
		return record;
	}

	public void addRecordsToGrid(List<CommitteeMember> committeeMembers) {
		ListGridRecord[] records = new ListGridRecord[committeeMembers.size()];
		int row = 0;
		for (CommitteeMember item : committeeMembers) {
			records[row] = addRowData(item);
			row++;
		}
		this.setData(records);

	}
	

}
