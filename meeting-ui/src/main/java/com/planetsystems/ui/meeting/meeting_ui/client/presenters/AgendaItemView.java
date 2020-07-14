/**
 * 
 */
package com.planetsystems.ui.meeting.meeting_ui.client.presenters;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.planetsystems.ui.meeting.meeting_ui.client.views.panes.AgendaItemPane;
import com.planetsystems.ui.meeting.meeting_ui.client.views.windows.CommitteeFormWindow;
import com.smartgwt.client.widgets.layout.VLayout;


public class AgendaItemView  implements AgendaItemPresenter.MyView {
	private VLayout panel;
	private AgendaItemPane agendaItemPane;
	private CommitteeFormWindow committeeFormWindow;

	@Inject
	public AgendaItemView() {
		panel = new VLayout();
		agendaItemPane = new AgendaItemPane();
		
		committeeFormWindow=new CommitteeFormWindow();
		panel.addMember(agendaItemPane);
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
	public AgendaItemPane getAgendaItemPane() {

		return agendaItemPane;
	}

	@Override
	public CommitteeFormWindow getCommitteeFormWindow() {
		// TODO Auto-generated method stub
		return committeeFormWindow;
	}

	


}
