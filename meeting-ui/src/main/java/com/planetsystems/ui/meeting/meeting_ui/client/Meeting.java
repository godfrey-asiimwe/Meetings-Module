package com.planetsystems.ui.meeting.meeting_ui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;
import com.planetsystems.ui.meeting.meeting_ui.client.gin.ClientGinjector;


public class Meeting implements EntryPoint {

	private final ClientGinjector ginjector = GWT.create(ClientGinjector.class);

	public void onModuleLoad() {
		DelayedBindRegistry.bind(ginjector);

		ginjector.getPlaceManager().revealCurrentPlace();
	}

}