/**
 * 
 */
package com.planetsystems.ui.meeting.meeting_ui.client.presenters;

import java.util.Date;
import java.util.LinkedHashMap;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
import com.planetsystems.model.meetings.Agenda;
import com.planetsystems.model.meetings.AgendaItems;
import com.planetsystems.model.meetings.CommitteeMember;
import com.planetsystems.model.meetings.Committees;
import com.planetsystems.model.meetings.Meetings;
import com.planetsystems.model.utils.utils_model.FinancialYear;
import com.planetsystems.model.utils.utils_model.Status;
import com.planetsystems.model.utils.utils_model.User;
import com.planetsystems.ui.meeting.meeting_ui.client.place.NameTokens;
import com.planetsystems.ui.meeting.meeting_ui.client.util.MeetingsConstants;
import com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids.AgendaListGrid;
import com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids.CommitteeListGrid;
import com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids.MeetingListGrid;
import com.planetsystems.ui.meeting.meeting_ui.client.views.panes.CommitteePane;
import com.planetsystems.ui.meeting.meeting_ui.client.views.windows.AgendaItemFormWindow;
import com.planetsystems.ui.meeting.meeting_ui.client.views.windows.CommitteeFormWindow;
import com.planetsystems.ui.meeting.meeting_ui.client.views.windows.CommitteeMemberFormWindow;
import com.planetsystems.ui.meeting.meeting_ui.client.views.windows.MeetingsFormWindow;
import com.planetsystems.ui.meeting.meeting_ui.shared.AgendaItemAction;
import com.planetsystems.ui.meeting.meeting_ui.shared.AgendaItemActionResult;
import com.planetsystems.ui.meeting.meeting_ui.shared.CommitteeAction;
import com.planetsystems.ui.meeting.meeting_ui.shared.CommitteeActionResult;
import com.planetsystems.ui.utils.utils_ui.client.utils.UtilConstants;
import com.planetsystems.ui.utils.utils_ui.shared.Util;
import com.planetsystems.ui.utils.utils_ui.shared.UtilResult;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellContextClickEvent;
import com.smartgwt.client.widgets.grid.events.CellContextClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordExpandEvent;
import com.smartgwt.client.widgets.grid.events.RecordExpandHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemSeparator;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;



public class CommitteePresenter extends Presenter<CommitteePresenter.MyView, CommitteePresenter.MyProxy>{

	final PlaceManager placeManager;
	final DispatchAsync dispatcher;

	/**
	 * @param eventBus
	 * @param view
	 * @param proxy
	 */
	@Inject
	public CommitteePresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);

		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}

	/**
	 * @author Planet Developer 001
	 * 
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.committees)
	public interface MyProxy extends Proxy<CommitteePresenter>, Place {

	}

	public interface MyView extends View {

		public CommitteePane getCommitteePane();
		public CommitteeFormWindow getCommitteeFormWindow();
	}

	@Override
	protected void onBind() {
		
		super.onBind();
		onNewButtonClicked();
		retrieveCommittees();
		onRightClickCommitteeListGrid();
		onCommitteeListGridExpanded();
		
	}

	private void onNewButtonClicked() {
		getView().getCommitteePane().getAddNewbutton().addClickHandler(new ClickHandler() {
					
					public void onClick(ClickEvent event) {
						CommitteeFormWindow committeeformWindow=new CommitteeFormWindow();
						
					    onSaveCommitteeButtonClicked(committeeformWindow);
					    
					    committeeformWindow.show();
					}

					
				});

		
	}
	
	 private void loadUserCombo(final CommitteeMemberFormWindow committeeMemberFormWindow) {
		  
		  dispatcher.execute(new Util(UtilConstants.RETRIEVE_USER), new
		  AsyncCallback<UtilResult>() { 
				  public void onFailure(Throwable caught) {
					  
			      System.out.println(caught.getMessage()); 
			  
				  }
			  
				  public void onSuccess(UtilResult result) { 
					  
					  if (result != null) {
				  LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						  for (User user : result.getUsers()) {
							  
						  valueMap.put(user.getId(), user.getUsername()); 
						  
						  }
				  
						  committeeMemberFormWindow.getUser().setValueMap(valueMap);
		  
					  	} else { 
			 
					  		System.out.println("loadUserCombo list is Empty"); 
			 
					  	}
		  
		  } }); 
	  
	  }
	  
	
	public void ClearControl() {
	
	}
	
	private void onSaveCommitteeButtonClicked(final CommitteeFormWindow committeeformWindow) {
			
			committeeformWindow.getSaveCommitteeButton().addClickHandler(new ClickHandler() {
	        	
				public void onClick(ClickEvent event) {
					Committees committee=new Committees();
					
					committee.setName(committeeformWindow.getName().getValueAsString());
					committee.setPurpose(committeeformWindow.getPurpose().getValueAsString());
					committee.setComment(committeeformWindow.getComment().getValueAsString());
					
					Date date=new Date();
					committee.setDateMade(date);
					
					committee.setStatus(Status.ACTIVE);
					
					
					dispatcher.execute(new CommitteeAction(MeetingsConstants.SAVE_COMMITTEE,committee),
					new AsyncCallback<CommitteeActionResult>() {
								public void onFailure(Throwable caught) {
									System.out.println("An Error Occurred: " + caught.getMessage());
								}
	
								public void onSuccess(CommitteeActionResult result) {
									if (result != null) {
										if (result.isReponse()) {
											
											SC.say("SUCCESS", "Successfully Saved");
											
											getView().getCommitteePane().getCommitteeListGrid().addRecordsToGrid(result.getCommittees());
											System.out.println("values "+result.getCommittees().size());
											committeeformWindow.clear();
										}

									} else {
										System.out.println("onSaveCommittee list is Empty");
									}
								}
					});
				}
			});
		
	}
	
	
	private void retrieveCommittees() {
		
		dispatcher.execute(new CommitteeAction(MeetingsConstants.RETRIEVE_COMMITTEE),
				new AsyncCallback<CommitteeActionResult>() {
							public void onFailure(Throwable caught) {
								System.out.println("An Error Occurred: " + caught.getMessage());
							}

							public void onSuccess(CommitteeActionResult result) {
								if (result != null) {
									
									getView().getCommitteePane().getCommitteeListGrid().addRecordsToGrid(result.getCommittees());
									
								} else {
									System.out.println("Committee list is Empty");
								}
							}
				});
		
	}
	
	
	private void onRightClickCommitteeListGrid() {

		getView().getCommitteePane().getCommitteeListGrid().addCellContextClickHandler(new CellContextClickHandler() {

			public void onCellContextClick(CellContextClickEvent event) {
				
				getView().getCommitteePane().getCommitteeListGrid().deselectAllRecords();
				getView().getCommitteePane().getCommitteeListGrid().selectRecord(event.getRecord());

				Menu menu = new Menu();

				MenuItem editItem = new MenuItem("Edit");
				MenuItem deleteItem = new MenuItem("Delete");
				MenuItem addMember=new MenuItem("Add Member");
				MenuItem deleteMember=new MenuItem("Delete Member");
				MenuItem editMember=new MenuItem("Edit Member");
				
				
				/* MenuItem markAsClearedItem = new MenuItem("Mark As Approved"); */

				editItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					public void onClick(MenuItemClickEvent event) {
						
						onCommitteeRecordSelectedToEdit();
					}
				});

				deleteItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					public void onClick(MenuItemClickEvent event) {

						SC.ask("COMFIRM", "Are You Sure You want to delete the Selected Committee?", new BooleanCallback() {

							public void execute(Boolean value) {
								if (value == true) {
									
									onDeleteCommittee();
									
								}

							}
						});

					}
				});
				
				addMember.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					public void onClick(MenuItemClickEvent event) {

						CommitteeMemberFormWindow committeeMemberFormWindow=new CommitteeMemberFormWindow();
						loadUserCombo(committeeMemberFormWindow);
						onSaveCommitteeMemberButtonCliked(committeeMemberFormWindow);
						committeeMemberFormWindow.show();

					}
				});
				
				deleteMember.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					public void onClick(MenuItemClickEvent event) {
						
						SC.ask("COMFIRM", "Are You Sure You want to delete the Selected CommitteeMember?", new BooleanCallback() {

							public void execute(Boolean value) {
								if (value == true) {
									
									//deleteAgendaItem();
									
								}

							}
						});

					}
				});
				
				editMember.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					public void onClick(MenuItemClickEvent event) {
						
						//onAgendaItemRecordSelectedToEdit();
					}
				});

				 menu.setItems(editItem, new MenuItemSeparator(),
						  deleteItem,new MenuItemSeparator(),addMember,new MenuItemSeparator(),deleteMember,new MenuItemSeparator(),editMember); 
				 
				 getView().getCommitteePane().getCommitteeListGrid().setContextMenu(menu);

			}
		});
	}
	
	private void onSaveCommitteeMemberButtonCliked(final CommitteeMemberFormWindow committeeMemberFormWindow) {
		committeeMemberFormWindow.getSaveCommitteeUserButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				CommitteeMember committeeMember=new CommitteeMember();
				
				committeeMember.setPosition(committeeMemberFormWindow.getposition().getValueAsString());
				committeeMember.setComment(committeeMemberFormWindow.getComment().getValueAsString());
				
				User user=new User();
				user.setId(committeeMemberFormWindow.getUser().getValueAsString());
				
				committeeMember.setUser(user);
				
				Committees committee=new Committees();
				committee.setId(getView().getCommitteePane().getCommitteeListGrid().getSelectedRecord()
							.getAttribute(CommitteeListGrid.COMMITTE_ID));
				
				committeeMember.setCommittee(committee);
				
				Date date=new Date();
				committeeMember.setDateMade(date);
				
				committeeMember.setStatus(Status.IN_ACTIVE);
				
				
				dispatcher.execute(new AgendaItemAction(committeeMember,MeetingsConstants.SAVE_COMMITTEE_MEMBER),
						new AsyncCallback<AgendaItemActionResult>() {
									public void onFailure(Throwable caught) {
										System.out.println("An Error Occurred: " + caught.getMessage());
									}
		
									public void onSuccess(AgendaItemActionResult result) {
										if (result != null) {
											if (result.isReponse()) {
												
												SC.say("SUCCESS", "Successfully Saved");
												
												getView().getCommitteePane().getCommitteeListGrid();
												CommitteeListGrid.getCommitteUserListGrid().addRecordsToGrid(result.getCommitteeMemberList());
												
												committeeMemberFormWindow.close();
											}

										} else {
											System.out.println("AgendaItem list is Empty");
										}
									}
						});
			}
		});
	}
	
private void onCommitteeListGridExpanded() {
		
	getView().getCommitteePane().getCommitteeListGrid().addRecordExpandHandler(new RecordExpandHandler() {
			
			@Override
			public void onRecordExpand(RecordExpandEvent event) {
				
				
				CommitteeMember committeeMember=new CommitteeMember();
				
				Committees committee=new Committees();
				
				committee.setId(getView().getCommitteePane().getCommitteeListGrid().getSelectedRecord()
						.getAttribute(CommitteeListGrid.COMMITTE_ID));
				
				committeeMember.setCommittee(committee);
				
				
				dispatcher.execute(new AgendaItemAction(committeeMember,MeetingsConstants.RETRIEVE_COMMITTEE_USER),
						new AsyncCallback<AgendaItemActionResult>() {
									public void onFailure(Throwable caught) {
										System.out.println("An Error Occurred: " + caught.getMessage());
									}

									public void onSuccess(AgendaItemActionResult result) {
										if (result != null) {
											
											getView().getCommitteePane().getCommitteeListGrid();
											CommitteeListGrid.getCommitteUserListGrid().addRecordsToGrid(result.getCommitteeMemberList());
											
											
										} else {
											System.out.println("Committee list is Empty");
										}
									}
						});

				
			}
		});
	}
	

    private void onDeleteCommittee() {
    	ListGridRecord record = getView().getCommitteePane().getCommitteeListGrid().getSelectedRecord();
		Committees committee = new Committees();
		committee.setId(record.getAttribute(CommitteeListGrid.COMMITTE_ID));
		
		System.out.println("committee Id delete"+committee.getId());
		
		dispatcher.execute(new CommitteeAction(MeetingsConstants.DELETE_COMMITTEE,committee),
				new AsyncCallback<CommitteeActionResult>() {
							public void onFailure(Throwable caught) {
								System.out.println("An Error Occurred: " + caught.getMessage());
							}

							public void onSuccess(CommitteeActionResult result) {
								if (result != null) {
									if (result.isReponse()) {
										
										SC.say("SUCCESS", "Successfully Deleted");
										
										getView().getCommitteePane().getCommitteeListGrid().addRecordsToGrid(result.getCommittees());
								
									}

								} else {
									System.out.println("onSaveCommittee list is Empty");
								}
							}
				});

    }
    
	public void onCommitteeRecordSelectedToEdit(){
		
		CommitteeFormWindow committeeFormWindow=new CommitteeFormWindow();
		
		committeeFormWindow.getName().setValue(getView().getCommitteePane().getCommitteeListGrid()
				.getSelectedRecord().getAttribute(CommitteeListGrid.COMMITTE_NAME));
		
		committeeFormWindow.getPurpose().setValue(getView().getCommitteePane().getCommitteeListGrid()
				.getSelectedRecord().getAttribute(CommitteeListGrid.COMMITTEE_PURPOSE));
		
		committeeFormWindow.getComment().setValue(getView().getCommitteePane().getCommitteeListGrid()
				.getSelectedRecord().getAttribute(CommitteeListGrid.COMMITTEE_COMMENT));
		
		
		editCommittee(committeeFormWindow);
		committeeFormWindow.show();
	 }
	

	private void editCommittee(final CommitteeFormWindow committeeFormWindow) {
		
		committeeFormWindow.getSaveCommitteeButton().addClickHandler(new ClickHandler() {
        	
			public void onClick(ClickEvent event) {
				Committees committee=new Committees();
				
				committee.setId(getView().getCommitteePane().getCommitteeListGrid()
				.getSelectedRecord().getAttribute(CommitteeListGrid.COMMITTE_ID));
				
				System.out.println("committee Id"+committee.getId());
				
				committee.setName(committeeFormWindow.getName().getValueAsString());
				committee.setPurpose(committeeFormWindow.getPurpose().getValueAsString());
				committee.setComment(committeeFormWindow.getComment().getValueAsString());
				
				Date date=new Date();
				committee.setDateMade(date);
				
				committee.setStatus(Status.ACTIVE);
				
				
				dispatcher.execute(new CommitteeAction(MeetingsConstants.EDIT_COMMITTEE,committee),
				new AsyncCallback<CommitteeActionResult>() {
							public void onFailure(Throwable caught) {
								System.out.println("An Error Occurred: " + caught.getMessage());
							}

							public void onSuccess(CommitteeActionResult result) {
								if (result != null) {
									if (result.isReponse()) {
										
										SC.say("SUCCESS", "Successfully Edited");
										
										getView().getCommitteePane().getCommitteeListGrid().addRecordsToGrid(result.getCommittees());

										committeeFormWindow.clear();
									}

								} else {
									System.out.println("onSaveCommittee list is Empty");
								}
							}
				});
			}
		});
	
		
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
