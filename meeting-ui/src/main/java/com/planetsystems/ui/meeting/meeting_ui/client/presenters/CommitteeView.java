/**
 * 
 */
package com.planetsystems.ui.meeting.meeting_ui.client.presenters;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.planetsystems.ui.meeting.meeting_ui.client.views.panes.CommitteePane;
import com.planetsystems.ui.meeting.meeting_ui.client.views.windows.CommitteeFormWindow;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Planet Developer 001
 * 
 */
public class CommitteeView  implements CommitteePresenter.MyView {
	private VLayout panel;
	private CommitteePane committeePane;
	private CommitteeFormWindow committeeFormWindow;

	@Inject
	public CommitteeView() {
		panel = new VLayout();
		committeePane = new CommitteePane();
		
		committeeFormWindow=new CommitteeFormWindow();
		panel.addMember(committeePane);
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
	public CommitteePane getCommitteePane() {

		return committeePane;
	}

	@Override
	public CommitteeFormWindow getCommitteeFormWindow() {
		// TODO Auto-generated method stub
		return committeeFormWindow;
	}

	


}
