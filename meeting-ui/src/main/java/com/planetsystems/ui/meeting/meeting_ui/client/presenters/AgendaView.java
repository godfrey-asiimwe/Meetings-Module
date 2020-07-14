/**
 * 
 */
package com.planetsystems.ui.meeting.meeting_ui.client.presenters;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.planetsystems.ui.meeting.meeting_ui.client.views.panes.AgendaItemPane;
import com.planetsystems.ui.meeting.meeting_ui.client.views.panes.AgendaPane;
import com.planetsystems.ui.meeting.meeting_ui.client.views.windows.AgendaFormWindow;
import com.planetsystems.ui.meeting.meeting_ui.client.views.windows.CommitteeFormWindow;
import com.smartgwt.client.widgets.layout.VLayout;


public class AgendaView  implements AgendaPresenter.MyView {
	private VLayout panel;
	private AgendaPane agendaPane;
	private CommitteeFormWindow committeeFormWindow;

	@Inject
	public AgendaView() {
		panel = new VLayout();
		agendaPane = new AgendaPane();
		
		committeeFormWindow=new CommitteeFormWindow();
		panel.addMember(agendaPane);
		panel.setWidth100();
		panel.setHeight("90%");

	}

	public Widget asWidget() {
		return panel;
	}

	public VLayout getPanel() {
		return panel;
	}

	@Override
	public void addToSlot(Object slot, Widget content) {
		
		
	}

	@Override
	public void removeFromSlot(Object slot, Widget content) {
		
		
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		
		
	}

	@Override
	public AgendaPane getAgendaPane() {

		return agendaPane;
	}

	@Override
	public AgendaFormWindow getAgendaFormWindow() {
		// TODO Auto-generated method stub
		return getAgendaFormWindow();
	}

	


}
