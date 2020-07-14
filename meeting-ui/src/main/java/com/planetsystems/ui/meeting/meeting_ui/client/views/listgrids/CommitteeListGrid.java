/**
 * 
 */
package com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids;


import java.util.List;

import com.planetsystems.model.meetings.Committees;
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

public class CommitteeListGrid extends SuperListGrid{

	public static String COMMITTE_ID="id";
	public static String COMMITTE_NAME="commitee_name";
	public static String COMMITTEE_PURPOSE="Committee_purpose";
	public static String COMMITTEE_COMMENT="Committee_comment";
	public static String COMMITTEE_DATE_CREATION="Committee_date_creation";
	public static String COMMITTEE_STATUS="Committee_Status";
	
	public static CommitteUserListGrid committeUserListGrid;


	/**
	 * constructor
	 */
	public CommitteeListGrid() {
		  super();
		  
		  ListGridField id = new ListGridField(COMMITTE_ID, "Id");
			id.setHidden(true);
			
		  ListGridField committename=new ListGridField(COMMITTE_NAME,"Name");
		  
		  
		  ListGridField Purpose=new ListGridField(COMMITTEE_PURPOSE,"Purpose");
		  
		  ListGridField Comment=new ListGridField(COMMITTEE_COMMENT,"Comment");
		  
		  ListGridField Date=new ListGridField(COMMITTEE_DATE_CREATION,"Date of Creation");
		  
		/* ListGridField Status=new ListGridField(COMMITTEE_STATUS,"Status"); */
		  
		  this.setFields(id,committename,Purpose,Comment,Date/*Status*/);
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
		
		committeUserListGrid=new CommitteUserListGrid();

		committeUserListGrid.setWidth100();
		committeUserListGrid.setHeight(224);
		committeUserListGrid.setCellHeight(22);

		layout.addMember(committeUserListGrid);

		
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
	
    public ListGridRecord addRowData(Committees committee) {
		
		ListGridRecord record = new ListGridRecord();

		record.setAttribute(COMMITTE_ID, committee.getId());

		record.setAttribute(COMMITTE_NAME, committee.getName());

		record.setAttribute(COMMITTEE_PURPOSE, committee.getPurpose());

		record.setAttribute(COMMITTEE_COMMENT, committee.getComment());
		
		record.setAttribute(COMMITTEE_DATE_CREATION, committee.getDateMade());

		/* record.setAttribute(COMMITTEE_STATUS, committee.getStatus()); */

		return record;
	}

	public void addRecordsToGrid(List<Committees> committees) {
		ListGridRecord[] records = new ListGridRecord[committees.size()];
		int row = 0;
		for (Committees item : committees) {
			records[row] = addRowData(item);
			row++;
		}
		
		this.setData(records);
		
	}
	
	
	public static CommitteUserListGrid getCommitteUserListGrid() {
		return committeUserListGrid;
	}

	public static void setCommitteUserListGrid(CommitteUserListGrid committeUserListGrid) {
		CommitteeListGrid.committeUserListGrid = committeUserListGrid;
	}
}
