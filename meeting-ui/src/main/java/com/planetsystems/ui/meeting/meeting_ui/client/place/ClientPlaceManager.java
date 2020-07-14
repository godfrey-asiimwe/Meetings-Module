package com.planetsystems.ui.meeting.meeting_ui.client.place;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

public class ClientPlaceManager extends PlaceManagerImpl {
	private final PlaceRequest defaultPlaceRequest;

	@Inject
	public ClientPlaceManager(EventBus eventBus, TokenFormatter tokenFormatter,
			@DefaultPlace String defaultNameToken) {
		super(eventBus, tokenFormatter);
		// TODO Auto-generated constructor stub
		this.defaultPlaceRequest = new PlaceRequest(defaultNameToken);
	}

	public void revealDefaultPlace() {
		// TODO Auto-generated method stub
		revealPlace(defaultPlaceRequest);

	}

}
