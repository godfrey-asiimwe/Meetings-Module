package com.planetsystems.ui.meeting.meeting_ui.client.views.windows;


import com.planetsystems.ui.utils.utils_ui.client.widgets.CommentField;
import com.planetsystems.ui.utils.utils_ui.client.widgets.DateField;
import com.planetsystems.ui.utils.utils_ui.client.widgets.SaveButton;
import com.planetsystems.ui.utils.utils_ui.client.widgets.TextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;

public class MeetingMinuteFormWindow extends Window{
	
	private TextField Action;
	private TextField personResponsible;
	private DateField deadLine;
	private CommentField comment;

	
	private SaveButton saveMinuteButton;

	private Label heading;

	public MeetingMinuteFormWindow() {
		super();
		
		heading = new Label();
		heading.setStyleName("crm-ContextArea-Header-Label");
		heading.setContents("Meeting Minutes");
		heading.setWidth("100%");
		heading.setAutoHeight();
		heading.setMargin(10);
		heading.setAlign(Alignment.LEFT);


		comment = new CommentField("shortDescription", "Short Description");

		saveMinuteButton = new SaveButton("Save");

		Action = new TextField();
		Action.setTitle("Action");
		
		personResponsible = new TextField();
		personResponsible.setTitle("Person Responsible");
		
		deadLine=new DateField();
		deadLine.setTitle("Deadline");

		DynamicForm form = new DynamicForm();
		form.setNumCols(4);
		form.setPadding(9); 
		form.setFields(Action, personResponsible,deadLine, comment);
		form.setTitleAlign(Alignment.CENTER);
		
		/* ITEM BUTTONS */

		/* END ITEM BUTTONS */

		/* BID BUTTONS */
		DynamicForm savebidButtonform = new DynamicForm();
		savebidButtonform.setFields(saveMinuteButton);

		/* END BID BUTTONS */


		VLayout requisitionbuttonHolder = new VLayout();

		requisitionbuttonHolder.setMembers(savebidButtonform);
		requisitionbuttonHolder.setWidth100();
		requisitionbuttonHolder.setAutoHeight();

		VLayout requisitionPart1Layout = new VLayout();
		requisitionPart1Layout.addMember(heading);
		requisitionPart1Layout.addMember(form);
		requisitionPart1Layout.addMember(requisitionbuttonHolder);
		requisitionPart1Layout.setMargin(5); 

		this.addItem(requisitionPart1Layout);
		this.setWidth("60%");
		this.setHeight("30%");
		this.setAutoCenter(true);
		this.setTitle("New Item");
		this.setIsModal(true);
		this.setShowModalMask(true);
	}

	public SaveButton getSaveMinuteButton() {
		return saveMinuteButton;
	}
	

	public Label getHeading() {
		return heading;
	}

	public CommentField getComment() {
		return comment;
	}


	/**
	 * @return the action
	 */
	public TextField getAction() {
		return Action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(TextField action) {
		Action = action;
	}

	/**
	 * @return the personResponsible
	 */
	public TextField getPersonResponsible() {
		return personResponsible;
	}

	/**
	 * @param personResponsible the personResponsible to set
	 */
	public void setPersonResponsible(TextField personResponsible) {
		this.personResponsible = personResponsible;
	}

	/**
	 * @return the deadLine
	 */
	public DateField getDeadLine() {
		return deadLine;
	}

	/**
	 * @param deadLine the deadLine to set
	 */
	public void setDeadLine(DateField deadLine) {
		this.deadLine = deadLine;
	}

	
}
