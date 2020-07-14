package com.planetsystems.ui.meeting.meeting_ui.client.views.windows;


import com.planetsystems.ui.utils.utils_ui.client.widgets.CommentField;
import com.planetsystems.ui.utils.utils_ui.client.widgets.DeleteButton;
import com.planetsystems.ui.utils.utils_ui.client.widgets.EditButton;
import com.planetsystems.ui.utils.utils_ui.client.widgets.SaveButton;
import com.planetsystems.ui.utils.utils_ui.client.widgets.TextField;
import com.planetsystems.ui.utils.utils_ui.client.widgets.UploadButton;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;

public class CommitteeFormWindow extends Window{
	
	private TextField name;
	private TextField purpose;
	private CommentField comment;

	private SaveButton addItemButton;
	private EditButton editItemButton;
	private DeleteButton deleteItemButton;
	private UploadButton uploadItemsButton;

	
	private SaveButton saveCommitteButton;

	private Label heading;
	private Label heading2;

	public CommitteeFormWindow() {
		super();
		
		heading = new Label();
		heading.setStyleName("crm-ContextArea-Header-Label");
		heading.setContents("a) Committee information");
		heading.setWidth("100%");
		heading.setAutoHeight();
		heading.setMargin(10);
		heading.setAlign(Alignment.LEFT);
		
		heading2 = new Label();
		heading2.setStyleName("crm-ContextArea-Header-Label");
		heading2.setContents("b) Committee User");
		heading2.setWidth("100%");
		heading2.setAutoHeight();
		heading2.setMargin(10);
		heading2.setAlign(Alignment.LEFT);


		comment = new CommentField("shortDescription", "Short Description");


		saveCommitteButton = new SaveButton("Save");

		name = new TextField();
		name.setTitle("Name");
		
		purpose = new TextField();
		purpose.setTitle("Purpose");


		DynamicForm form = new DynamicForm();
		form.setNumCols(4);
		form.setPadding(9); 
		form.setFields(name, purpose, comment);
		form.setTitleAlign(Alignment.CENTER);

		
		
		addItemButton=new SaveButton("Add Member");
		
		DynamicForm form2 = new DynamicForm();
		form2.setNumCols(4);
		form2.setPadding(9); 
		form2.setTitleAlign(Alignment.CENTER);
		
		/* ITEM BUTTONS */
		

		
		DynamicForm addnewitembuttonform = new DynamicForm();
		addnewitembuttonform.setFields(addItemButton);
		
		DynamicForm edititembuttonform = new DynamicForm();
		edititembuttonform.setFields(editItemButton);

		DynamicForm deleteitembuttonform = new DynamicForm();
		deleteitembuttonform.setFields(deleteItemButton);

		DynamicForm uploadItemsButtonform = new DynamicForm();
		uploadItemsButtonform.setFields(uploadItemsButton);

		/* END ITEM BUTTONS */

		/* BID BUTTONS */
		DynamicForm savebidButtonform = new DynamicForm();
		savebidButtonform.setFields(saveCommitteButton);

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
		this.setHeight("35%");
		this.setAutoCenter(true);
		this.setTitle("New Committee");
		this.setIsModal(true);
		this.setShowModalMask(true);
	}

	public SaveButton getAddItemButton() {
		return addItemButton;
	}

	public EditButton getEditItemButton() {
		return editItemButton;
	}

	public DeleteButton getDeleteItemButton() {
		return deleteItemButton;
	}

	public UploadButton getUploadItemsButton() {
		return uploadItemsButton;
	}

	public SaveButton getSaveCommitteeButton() {
		return saveCommitteButton;
	}
	

	public Label getHeading() {
		return heading;
	}

	public CommentField getComment() {
		return comment;
	}
	
	public TextField getName() {
		return name;
	}
	
	public TextField getPurpose() {
		return purpose;
	}


}
