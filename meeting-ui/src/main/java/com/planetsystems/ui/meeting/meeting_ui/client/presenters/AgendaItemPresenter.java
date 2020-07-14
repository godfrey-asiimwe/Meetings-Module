/**
 * 
 */
package com.planetsystems.ui.meeting.meeting_ui.client.presenters;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.planetsystems.ui.meeting.meeting_ui.client.place.NameTokens;
import com.planetsystems.ui.meeting.meeting_ui.client.views.panes.AgendaItemPane;
import com.planetsystems.ui.meeting.meeting_ui.client.views.windows.CommitteeFormWindow;



public class AgendaItemPresenter extends Presenter<AgendaItemPresenter.MyView, AgendaItemPresenter.MyProxy>{

	final PlaceManager placeManager;
	final DispatchAsync dispatcher;

	/**
	 * @param eventBus
	 * @param view
	 * @param proxy
	 */
	@Inject
	public AgendaItemPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);

		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
		
	}

	
	@ProxyCodeSplit
	@NameToken(NameTokens.agendaItems)
	public interface MyProxy extends Proxy<AgendaItemPresenter>, Place {

	}

	public interface MyView extends View {

		public AgendaItemPane getAgendaItemPane();
		public CommitteeFormWindow getCommitteeFormWindow();
	}

	@Override
	protected void onBind() {
		
		super.onBind();
		
	}


	
	public void ClearControl() {
	
	}
	
	


	@Override
	protected void onReset() {
		super.onReset();
	}

	
	@Override
	protected void revealInParent() {

		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetContextAreaContent, this);

	}

	/* (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.events.ContractsUiHandlers#onCompleteTaskButtonClicked()
	 */
	public void onCompleteTaskButtonClicked() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.events.ContractsUiHandlers#onStartMeetingButtonClicked(java.lang.String)
	 */
	public void onStartMeetingButtonClicked(String taskid) {
		// TODO Auto-generated method stub
		
	}

}
