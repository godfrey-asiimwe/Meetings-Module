package com.planetsystems.ui.meeting.meeting_ui.client.views.windows;


import com.planetsystems.ui.utils.utils_ui.client.widgets.ComboBox;
import com.planetsystems.ui.utils.utils_ui.client.widgets.CommentField;
import com.planetsystems.ui.utils.utils_ui.client.widgets.SaveButton;
import com.planetsystems.ui.utils.utils_ui.client.widgets.TextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;

public class CommitteeMemberFormWindow extends Window{
	
	private TextField position;
	private CommentField comment;
	private ComboBox user;

	
	private SaveButton saveCommitteeMemberButton;

	private Label heading;

	public CommitteeMemberFormWindow() {
		super();
		
		heading = new Label();
		heading.setStyleName("crm-ContextArea-Header-Label");
		heading.setContents("Committee Member information");
		heading.setWidth("100%");
		heading.setAutoHeight();
		heading.setMargin(10);
		heading.setAlign(Alignment.LEFT);


		comment = new CommentField("shortDescription", "Short Description");
		
		user=new ComboBox();
		user.setTitle("User");


		saveCommitteeMemberButton = new SaveButton("Save");

		position = new TextField();
		position.setTitle("Position");


		DynamicForm form = new DynamicForm();
		form.setNumCols(4);
		form.setPadding(9); 
		form.setFields(user, position, comment);
		form.setTitleAlign(Alignment.CENTER);
		
		/* ITEM BUTTONS */

		/* END ITEM BUTTONS */

		/* BID BUTTONS */
		DynamicForm savebidButtonform = new DynamicForm();
		savebidButtonform.setFields(saveCommitteeMemberButton);

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

	public SaveButton getSaveCommitteeUserButton() {
		return saveCommitteeMemberButton;
	}
	

	public Label getHeading() {
		return heading;
	}

	public CommentField getComment() {
		return comment;
	}
	
	public TextField getposition() {
		return position;
	}

	/**
	 * @return the user
	 */
	public ComboBox getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setAgenda(ComboBox agenda) {
		this.user = agenda;
	}

	
}
