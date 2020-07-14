package com.planetsystems.ui.meeting.meeting_ui.client.views.windows;


import com.planetsystems.ui.utils.utils_ui.client.widgets.ComboBox;
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

public class AgendaItemFormWindow extends Window{
	
	private TextField rank;
	private TextField action;
	private CommentField comment;
	private ComboBox agenda;

	private SaveButton addItemButton;
	private EditButton editItemButton;
	private DeleteButton deleteItemButton;
	private UploadButton uploadItemsButton;

	
	private SaveButton saveAgendaItemButton;

	private Label heading;

	public AgendaItemFormWindow() {
		super();
		
		heading = new Label();
		heading.setStyleName("crm-ContextArea-Header-Label");
		heading.setContents("a) Committee information");
		heading.setWidth("100%");
		heading.setAutoHeight();
		heading.setMargin(10);
		heading.setAlign(Alignment.LEFT);


		comment = new CommentField("shortDescription", "Short Description");
		
		agenda=new ComboBox();
		agenda.setTitle("Agenda");


		saveAgendaItemButton = new SaveButton("Save");

		rank = new TextField();
		rank.setTitle("Rank");
		
		action = new TextField();
		action.setTitle("Action");


		DynamicForm form = new DynamicForm();
		form.setNumCols(4);
		form.setPadding(9); 
		form.setFields(action, rank, comment);
		form.setTitleAlign(Alignment.CENTER);
		
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
		savebidButtonform.setFields(saveAgendaItemButton);

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

	public SaveButton getSaveAgendaItemButton() {
		return saveAgendaItemButton;
	}
	

	public Label getHeading() {
		return heading;
	}

	public CommentField getComment() {
		return comment;
	}
	
	public TextField getRank() {
		return rank;
	}
	
	public TextField getAction() {
		return action;
	}

	/**
	 * @return the agenda
	 */
	public ComboBox getAgenda() {
		return agenda;
	}

	/**
	 * @param agenda the agenda to set
	 */
	public void setAgenda(ComboBox agenda) {
		this.agenda = agenda;
	}

	
}
