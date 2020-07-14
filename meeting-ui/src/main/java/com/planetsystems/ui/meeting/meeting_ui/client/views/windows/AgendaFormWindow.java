package com.planetsystems.ui.meeting.meeting_ui.client.views.windows;


import com.planetsystems.ui.utils.utils_ui.client.widgets.CommentField;
import com.planetsystems.ui.utils.utils_ui.client.widgets.SaveButton;
import com.planetsystems.ui.utils.utils_ui.client.widgets.TextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;

public class AgendaFormWindow extends Window{
	
	private TextField name;
	private TextField agendaNo;
	private CommentField description;
	
	private SaveButton saveAgendaButton;

	private Label heading;

	public AgendaFormWindow() {
		super();
		
		heading = new Label();
		heading.setStyleName("crm-ContextArea-Header-Label");
		heading.setContents("a) Agenda ");
		heading.setWidth("100%");
		heading.setAutoHeight();
		heading.setMargin(10);
		heading.setAlign(Alignment.LEFT);


		description = new CommentField("shortDescription", "Short Description");


		saveAgendaButton = new SaveButton("Save");

		name = new TextField();
		name.setTitle("Name");
		
		agendaNo = new TextField();
		agendaNo.setTitle("NO");

		DynamicForm form = new DynamicForm();
		form.setNumCols(4);
		form.setPadding(9); 
		form.setFields(name, description);
		form.setTitleAlign(Alignment.CENTER);

		/* END ITEM BUTTONS */

		/* BID BUTTONS */
		DynamicForm savebidButtonform = new DynamicForm();
		savebidButtonform.setFields(saveAgendaButton);

		/* END BID BUTTONS */

		VLayout buttonHolder = new VLayout();

		buttonHolder.setMembers(savebidButtonform);
		buttonHolder.setWidth100();
		buttonHolder.setAutoHeight();

		VLayout requisitionPart1Layout = new VLayout();
		requisitionPart1Layout.addMember(heading);
		requisitionPart1Layout.addMember(form);
		requisitionPart1Layout.addMember(buttonHolder);
		requisitionPart1Layout.setMargin(5); 

		this.addItem(requisitionPart1Layout);
		this.setWidth("60%");
		this.setHeight("30%");
		this.setAutoCenter(true);
		this.setTitle("New Agenda");
		this.setIsModal(true);
		this.setShowModalMask(true);
	}
	
	public SaveButton getSaveAgendaItemButton() {
		return saveAgendaButton;
	}
	

	public Label getHeading() {
		return heading;
	}

	public CommentField getDSC() {
		return description;
	}
	
	public TextField getName() {
		return name;
	}
	
	public TextField getNo() {
		return agendaNo;
	}

	
}
