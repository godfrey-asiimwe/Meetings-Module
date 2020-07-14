package com.planetsystems.ui.meeting.meeting_ui.client.presenters;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.planetsystems.ui.meeting.meeting_ui.client.views.panes.MeetingsPane;
import com.smartgwt.client.widgets.layout.VLayout;

public class MeetingView extends ViewImpl implements MeetingsPresenter.MyView {
	private static final String DEFAULT_MARGIN = "0px";
	private VLayout panel;
	private MeetingsPane meetingsPane;

	@Inject
	public MeetingView() {
		panel = new VLayout();
		meetingsPane = new MeetingsPane();

		panel.addMember(meetingsPane);
		panel.setWidth100();
		panel.setHeight("90%");
		Window.enableScrolling(false);
		Window.setMargin(DEFAULT_MARGIN);

	}

	public Widget asWidget() {
		return panel;
	}

	public VLayout getPanel() {
		return panel;
	}
	

	public MeetingsPane getMeetingPane() {
		return meetingsPane;
	}


}
