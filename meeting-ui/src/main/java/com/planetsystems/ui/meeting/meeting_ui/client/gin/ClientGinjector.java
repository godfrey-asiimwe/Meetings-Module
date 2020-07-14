package com.planetsystems.ui.meeting.meeting_ui.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.AgendaItemPresenter;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.AgendaPresenter;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.MeetingsPresenter;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.CommitteePresenter;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.MainPagePresenter;
import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;

@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {
	PlaceManager getPlaceManager();

	EventBus getEventBus();

	AsyncProvider<MainPagePresenter> getMainPagePresenter();

	AsyncProvider<MeetingsPresenter> getBudgetPresenter();
	
	AsyncProvider<CommitteePresenter> getCommitteePresenter();
	
	AsyncProvider<AgendaItemPresenter> getAgendaItemPresenter();
	
	AsyncProvider<AgendaPresenter> getAgendaPresenter();

}
