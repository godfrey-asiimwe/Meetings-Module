package com.planetsystems.ui.meeting.meeting_ui.client.views.panes;

import com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids.MeetingListGrid;
import com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids.CommitteMemberListGrid;
import com.planetsystems.ui.utils.utils_ui.client.widgets.ComboBox;
import com.planetsystems.ui.utils.utils_ui.client.widgets.ControlsPane;
import com.planetsystems.ui.utils.utils_ui.client.widgets.DateField;
import com.planetsystems.ui.utils.utils_ui.client.widgets.LoadButton;
import com.planetsystems.ui.utils.utils_ui.client.widgets.PreviewButton;
import com.planetsystems.ui.utils.utils_ui.client.widgets.SaveButton;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class MeetingsPane extends VLayout {
	private CommitteMemberListGrid committeMemberListGrid;
	private MeetingListGrid meetingListGrid;
	private ComboBox financialYear;

	private LoadButton loadRequisitionsButton;
	private PreviewButton previewReportButton;
	private PreviewButton importButton;
	private SaveButton addNewbutton;
	private DateField fromDate;
	private DateField toDate;
	private ComboBox status;

	private ControlsPane controlsPane;

	public MeetingsPane() {
		super();

		Label header = new Label();
		header = new Label();
		header.setStyleName("crm-ContextArea-Header-Label");
		header.setContents("Meeting");
		header.setWidth("100%");
		header.setAutoHeight();
		header.setMargin(10);
		header.setAlign(Alignment.LEFT);

		controlsPane = new ControlsPane();

		committeMemberListGrid = new CommitteMemberListGrid();
		
		meetingListGrid=new MeetingListGrid();

		financialYear = new ComboBox();
		financialYear.setTitle("Financial Year");

		fromDate = new DateField();
		fromDate.setTitle("From");

		toDate = new DateField();
		toDate.setTitle("To");

		status = new ComboBox();
		status.setTitle("Status");

		previewReportButton = new PreviewButton("Preview Report");
		previewReportButton.setAutoFit(true);
		
		importButton = new PreviewButton("Import Button");
		importButton.setAutoFit(true);


		loadRequisitionsButton = new LoadButton("Load");

		addNewbutton = new SaveButton("New");

		DynamicForm dynamicForm = new DynamicForm();
		dynamicForm.setFields(financialYear);
		dynamicForm.setNumCols(8);
		dynamicForm.setWrapItemTitles(true);

		DynamicForm loadRequisitionsButtonform = new DynamicForm();
		loadRequisitionsButtonform.setFields(loadRequisitionsButton);
		loadRequisitionsButtonform.setAutoWidth();

		DynamicForm addNewbuttonform = new DynamicForm();
		addNewbuttonform.setFields(addNewbutton);
		addNewbuttonform.setAutoWidth();

		DynamicForm previewReportButtonform = new DynamicForm();
		previewReportButtonform.setFields(previewReportButton);
		previewReportButtonform.setAutoWidth();
		
		DynamicForm importButtonform = new DynamicForm();
		importButtonform.setFields(importButton);
		importButtonform.setAutoWidth();

		HLayout hLayout = new HLayout();
		hLayout.setMembers(loadRequisitionsButtonform, previewReportButtonform, addNewbuttonform);
		hLayout.setAutoHeight();
		hLayout.setMargin(1);

		VLayout layout = new VLayout();
		layout.addMember(controlsPane);
		layout.addMember(header);
		layout.addMember(dynamicForm);
		layout.addMember(hLayout);
		layout.addMember(meetingListGrid);

		this.addMember(layout);

	}


	public ComboBox getFinancialYear() {
		return financialYear;
	}

	public LoadButton getLoadRequisitionsButton() {
		return loadRequisitionsButton;
	}

	public SaveButton getAddNewbutton() {
		return addNewbutton;
	}

	public PreviewButton getPreviewReportButton() {
		return previewReportButton;
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


	public CommitteMemberListGrid getMeetingsListGrid() {
		return committeMemberListGrid;
	}


	/**
	 * @return the importButton
	 */
	public PreviewButton getImportButton() {
		return importButton;
	}


	/**
	 * @param importButton the importButton to set
	 */
	public void setImportButton(PreviewButton importButton) {
		this.importButton = importButton;
	}


	/**
	 * @return the meetingListGrid
	 */
	public MeetingListGrid getMeetingListGrid() {
		return meetingListGrid;
	}


	/**
	 * @param meetingListGrid the meetingListGrid to set
	 */
	public void setMeetingListGrid(MeetingListGrid meetingListGrid) {
		this.meetingListGrid = meetingListGrid;
	}
	
	

}
