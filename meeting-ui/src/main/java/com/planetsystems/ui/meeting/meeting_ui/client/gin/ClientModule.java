package com.planetsystems.ui.meeting.meeting_ui.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.planetsystems.ui.meeting.meeting_ui.client.place.ClientPlaceManager;
import com.planetsystems.ui.meeting.meeting_ui.client.place.DefaultPlace;
import com.planetsystems.ui.meeting.meeting_ui.client.place.NameTokens;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.AgendaItemPresenter;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.AgendaItemView;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.AgendaPresenter;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.AgendaView;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.MeetingsPresenter;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.MeetingView;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.CommitteePresenter;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.CommitteeView;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.MainPagePresenter;
import com.planetsystems.ui.meeting.meeting_ui.client.presenters.MainPageView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		install(new DefaultModule(ClientPlaceManager.class));
		
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.meeting);

		bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class,
				MainPageView.class, MainPagePresenter.MyProxy.class);

		bindPresenter(MeetingsPresenter.class, MeetingsPresenter.MyView.class,
				MeetingView.class, MeetingsPresenter.MyProxy.class);
		
		bindPresenter(CommitteePresenter.class,CommitteePresenter.MyView.class,
				CommitteeView.class,CommitteePresenter.MyProxy.class);
		
		bindPresenter(AgendaItemPresenter.class,AgendaItemPresenter.MyView.class,
				AgendaItemView.class,AgendaItemPresenter.MyProxy.class);
		
		bindPresenter(AgendaPresenter.class,AgendaPresenter.MyView.class,
				AgendaView.class,AgendaPresenter.MyProxy.class);

	}

}