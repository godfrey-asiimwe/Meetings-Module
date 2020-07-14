package com.planetsystems.ui.meeting.meeting_ui.client.presenters;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.smartgwt.client.widgets.layout.VLayout;

public class MainPageView extends ViewImpl implements MainPagePresenter.MyView {

	private VLayout contentPanel;
	private VLayout panel;


	@Inject
	public MainPageView() {
		super();
		contentPanel = new VLayout();
		contentPanel.setWidth("100%");
		contentPanel.setHeight("100%");

		panel = new VLayout();
		panel.setWidth100();
		panel.setHeight100();
		panel.setBackgroundColor("#fff");

		VLayout mastHead = new VLayout();

		panel.addMember(mastHead);
		panel.addMember(contentPanel);

	}

	public Widget asWidget() {
		return panel;
	}



	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == MainPagePresenter.TYPE_SetContextAreaContent) {
			contentPanel.setMembers((VLayout) content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	public VLayout getContentPanel() {
		return contentPanel;
	}

}
