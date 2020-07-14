package com.planetsystems.ui.meeting.meeting_ui.client.presenters;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.planetsystems.model.meetings.Agenda;
import com.planetsystems.model.meetings.AgendaItems;
import com.planetsystems.model.meetings.CommitteeMember;
import com.planetsystems.model.meetings.Committees;
import com.planetsystems.model.meetings.Meetings;
import com.planetsystems.model.meetings.Minutes;
import com.planetsystems.model.utils.utils_model.FinancialYear;
import com.planetsystems.model.utils.utils_model.Status;
import com.planetsystems.ui.meeting.meeting_ui.client.place.NameTokens;
import com.planetsystems.ui.meeting.meeting_ui.client.util.MeetingsConstants;
import com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids.AgendaItemListGrid;
import com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids.MeetingListGrid;
import com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids.MeetingMinuteListGrid;
import com.planetsystems.ui.meeting.meeting_ui.client.views.panes.MeetingsPane;
import com.planetsystems.ui.meeting.meeting_ui.client.views.windows.MeetingMinuteFormWindow;
import com.planetsystems.ui.meeting.meeting_ui.client.views.windows.MeetingsFormWindow;
import com.planetsystems.ui.meeting.meeting_ui.shared.AgendaItemAction;
import com.planetsystems.ui.meeting.meeting_ui.shared.AgendaItemActionResult;
import com.planetsystems.ui.meeting.meeting_ui.shared.CommitteeAction;
import com.planetsystems.ui.meeting.meeting_ui.shared.CommitteeActionResult;
import com.planetsystems.ui.meeting.meeting_ui.shared.MeetingsAction;
import com.planetsystems.ui.meeting.meeting_ui.shared.MeetingsResult;
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

public class MeetingsPresenter extends Presenter<MeetingsPresenter.MyView, MeetingsPresenter.MyProxy> {

	private final DispatchAsync dispatcher;

	public interface MyView extends View {
		public MeetingsPane getMeetingPane();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.meeting)
	public interface MyProxy extends ProxyPlace<MeetingsPresenter> {
	}

	@Inject
	public MeetingsPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
			final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetContextAreaContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		onNewButtonClicked();
		loadFinancialYearCombo();
		onRetrieveMeetings();
		onMeetingListGridExpanded();
		onRightClickMeetingListGrid();
		JsonTest();
	}

	
	
	 private void onNewButtonClicked() {
		 
		 getView().getMeetingPane().getAddNewbutton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				MeetingsFormWindow meetingFormWindow=new MeetingsFormWindow();
				loadCommiteeCombo(meetingFormWindow);
				loadFinancialYearCombo(meetingFormWindow);
				loadAgendaCombo(meetingFormWindow);
				onSaveButtonClicked(meetingFormWindow);
				
				meetingFormWindow.show();
				
			}
		});
	 }
	
	  
	  
	 
	  private void loadFinancialYearCombo() {
	  
		  dispatcher.execute(new Util(UtilConstants.RETRIEVE_FINANCIAL_YEAR), new
		  AsyncCallback<UtilResult>() { 
				  public void onFailure(Throwable caught) {
					  
			      System.out.println(caught.getMessage()); 
			  
				  }
			  
				  public void onSuccess(UtilResult result) { 
					  
					  if (result != null) {
				  LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						  for (FinancialYear financialYear : result.getFinancialYears()) {
							  
						  valueMap.put(financialYear.getId(), financialYear.getName()); 
						  
						  }
				  
						  	getView().getMeetingPane().getFinancialYear().setValueMap(valueMap);
		  
					  	} else { 
			 
					  		System.out.println("loadFinancialYearCombo list is Empty"); 
			 
					  	}
		  
		  } }); 
	  
	  }
	  
	  private void loadFinancialYearCombo(final MeetingsFormWindow meetingFormWindow) {
		  
		  dispatcher.execute(new Util(UtilConstants.RETRIEVE_FINANCIAL_YEAR), new
		  AsyncCallback<UtilResult>() { 
				  public void onFailure(Throwable caught) {
					  
			      System.out.println(caught.getMessage()); 
			  
				  }
			  
				  public void onSuccess(UtilResult result) { 
					  
					  if (result != null) {
				  LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						  for (FinancialYear financialYear : result.getFinancialYears()) {
							  
						  valueMap.put(financialYear.getId(), financialYear.getName()); 
						  
						  }
				  
						  meetingFormWindow.getFinancialYearCombo().setValueMap(valueMap);
		  
					  	} else { 
			 
					  		System.out.println("loadFinancialYearCombo list is Empty"); 
			 
					  	}
		  
		  } }); 
	  
	  }
	 
	  
	  private void loadAgendaCombo(final MeetingsFormWindow meetingFormWindow) {
		  
			dispatcher.execute(new CommitteeAction(MeetingsConstants.RETRIEVE_AGENDA),
					new AsyncCallback<CommitteeActionResult>() {
								public void onFailure(Throwable caught) {
									
									System.out.println("An Error Occurred: " + caught.getMessage());
									
								}

								public void onSuccess(CommitteeActionResult result) {
									  if (result != null) {
										  LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
												  for (Agenda agenda : result.getAgendaList()) {
													  
												  valueMap.put(agenda.getId(), agenda.getName()); 
												  
												  }
										  
												  meetingFormWindow.getAgendaCombo().setValueMap(valueMap);
								  
											  	} else { 
									 
											  		System.out.println("loadAgenda list is Empty"); 
									 
											  	}
								}
					});
	  }
	  
	  private void loadCommiteeCombo(final MeetingsFormWindow meetingFormWindow) {
		  
		  dispatcher.execute(new CommitteeAction(MeetingsConstants.RETRIEVE_COMMITTEE),
					new AsyncCallback<CommitteeActionResult>() {
								public void onFailure(Throwable caught) {
									System.out.println("An Error Occurred: " + caught.getMessage());
								}

								public void onSuccess(CommitteeActionResult result) {
									if (result != null) {
										  LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
												  for (Committees committee : result.getCommittees()) {
													  
												  valueMap.put(committee.getId(), committee.getName()); 
												  
									              }
										  
												  meetingFormWindow.getCommitteeCombo().setValueMap(valueMap);
								  
									} else { 
									 
									System.out.println("loadCommittee list is Empty"); 
									 
									}
								}
					});
		  
	  }
	  
	  private void onSaveButtonClicked(final MeetingsFormWindow meetingsformWindow) {
		  
		  meetingsformWindow.getSaveCommitteeButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Meetings meeting=new Meetings();
				meeting.setTitle(meetingsformWindow.getName().getValueAsString());
				meeting.setVenue(meetingsformWindow.getVenue().getValueAsString());
				meeting.setComment(meetingsformWindow.getComment().getValueAsString());
				meeting.setStatus(Status.ACTIVE);
				
				FinancialYear financialYear=new FinancialYear();
				financialYear.setId(meetingsformWindow.getFinancialYearCombo().getValueAsString());
				
				meeting.setFinancialYear(financialYear);
				
				Agenda agenda=new Agenda();
				agenda.setId(meetingsformWindow.getAgendaCombo().getValueAsString());
				
				meeting.setAgenda(agenda);
				
				Committees committee=new Committees();
				committee.setId(meetingsformWindow.getCommitteeCombo().getValueAsString());
				
				meeting.setCommittee(committee);
				
				Date date=new Date();
				meeting.setDate(date);
				
								
				dispatcher.execute(new MeetingsAction(MeetingsConstants.SAVE_MEETINGS,meeting),
						new AsyncCallback<MeetingsResult>() {
									public void onFailure(Throwable caught) {
										System.out.println("An Error Occurred: " + caught.getMessage());
									}
		
									public void onSuccess(MeetingsResult result) {
										if (result != null) {
											if (result.isReponse()) {
												
												SC.say("SUCCESS", "Successfully Saved");
												
												getView().getMeetingPane().getMeetingListGrid().addRecordsToGrid(result.getMeetingsList());

												meetingsformWindow.clear();
											}

										} else {
											System.out.println("Meetings list is Empty");
										}
									}
						});
				
				
			}
		});
	  }
	  
	  
	  private void onRetrieveMeetings() {
		  
		  dispatcher.execute(new MeetingsAction(MeetingsConstants.RETRIEVE_MEETINGS),
					new AsyncCallback<MeetingsResult>() {
								public void onFailure(Throwable caught) {
									System.out.println("An Error Occurred: " + caught.getMessage());
								}
	
								public void onSuccess(MeetingsResult result) {
									if (result != null) {
										
											getView().getMeetingPane().getMeetingListGrid().addRecordsToGrid(result.getMeetingsList());
											
									} else {
										System.out.println("Meetings list is Empty");
									}
								}
					});
	  }
	  
	  
		private void onMeetingListGridExpanded() {
			
			getView().getMeetingPane().getMeetingListGrid().addRecordExpandHandler(new RecordExpandHandler() {
				
				@Override
				public void onRecordExpand(RecordExpandEvent event) {
					
					getView().getMeetingPane().getMeetingListGrid().deselectAllRecords();
					getView().getMeetingPane().getMeetingListGrid().selectRecord(event.getRecord());
					
					
					AgendaItems agendaItem=new AgendaItems();
					
					Agenda agenda=new Agenda();
					
					agenda.setId(getView().getMeetingPane().getMeetingListGrid().getSelectedRecord()
							.getAttribute(MeetingListGrid.AGENDA_ID));
					
					agendaItem.setAgenda(agenda);
					
					
					dispatcher.execute(new AgendaItemAction(MeetingsConstants.RETRIEVE_AGENDA_ITEM_BYAGENDA,agendaItem),
							new AsyncCallback<AgendaItemActionResult>() {
										public void onFailure(Throwable caught) {
											System.out.println("An Error Occurred: " + caught.getMessage());
										}

										public void onSuccess(AgendaItemActionResult result) {
											if (result != null) {
												
												getView().getMeetingPane().getMeetingListGrid();
												MeetingListGrid.getAgendaItemListGrid().addRecordsToGrid(result.getAgendaItemList());
											} else {
												System.out.println("Committee list is Empty");
											}
										}
							});
					
					
					CommitteeMember committeeMember=new CommitteeMember();
					
					Committees committee=new Committees();
					
					committee.setId(getView().getMeetingPane().getMeetingListGrid().getSelectedRecord()
							.getAttribute(MeetingListGrid.COMMITTE_ID));
					
					committeeMember.setCommittee(committee);
					
					dispatcher.execute(new AgendaItemAction(committeeMember,MeetingsConstants.RETRIEVE_COMMITTEE_USER),
							new AsyncCallback<AgendaItemActionResult>() {
										public void onFailure(Throwable caught) {
											System.out.println("An Error Occurred: " + caught.getMessage());
										}

										public void onSuccess(AgendaItemActionResult result) {
											if (result != null) {
												
												getView().getMeetingPane().getMeetingListGrid();
												MeetingListGrid.getCommitteMemberListGrid().addRecordsToGrid(result.getCommitteeMemberList());
												
												
											} else {
												System.out.println("Committee list is Empty");
											}
										}
							});
					
					Minutes minute=new Minutes();
					
					ListGridRecord record = getView().getMeetingPane().getMeetingListGrid().getSelectedRecord();
					Meetings meeting = new Meetings();
					meeting.setId(record.getAttribute(MeetingListGrid.MEETINGS_ID));
					
					minute.setMeeting(meeting);
					
					System.out.println("Meeting Id on minute"+minute.getMeeting().getId());
				
					  dispatcher.execute(new MeetingsAction(MeetingsConstants.RETRIEVE_MEETINGS_MINUTES),
								new AsyncCallback<MeetingsResult>() {
											public void onFailure(Throwable caught) {
												System.out.println("An Error Occurred: " + caught.getMessage());
											}
				
											public void onSuccess(MeetingsResult result) {
												if (result != null) {
													
													getView().getMeetingPane().getMeetingListGrid();
													MeetingListGrid.getMeetingMinuteListGrid().addRecordsToGrid(result.getMinutesList());


												} else {
													System.out.println("Meetings list is Empty www");
												}
											}
								});
					  
						
				/*
				 * getView().getMeetingPane().getMeetingListGrid();
				 * agendaItem.setId(MeetingListGrid.getAgendaItemListGrid().getSelectedRecord()
				 * .getAttribute(AgendaItemListGrid.AGENDA_ITEM_ID));
				 * 
				 * minute.setAgendaItem(agendaItem);
				 * 
				 * dispatcher.execute(new MeetingsAction(minute,MeetingsConstants.
				 * RETRIEVE_MEETINGS_MINUTES_BYAGENDAITEM), new AsyncCallback<MeetingsResult>()
				 * { public void onFailure(Throwable caught) {
				 * System.out.println("An Error Occurred: " + caught.getMessage()); }
				 * 
				 * public void onSuccess(MeetingsResult result) { if (result != null) {
				 * 
				 * // getView().getMeetingPane().getMeetingListGrid(); //
				 * MeetingListGrid.getAgendaItemListGrid(); //
				 * AgendaItemListGrid.getMeetingMinuteListGrid().addRecordsToGrid(result.
				 * getMinutesList());
				 * 
				 * 
				 * } else { System.out.println("Meetings list is Empty www"); } } });
				 */
					  
					 
				}
			});
		}

		private void onAgendaItemListGridExpanded() {
			
			getView().getMeetingPane().getMeetingListGrid();
			MeetingListGrid.getAgendaItemListGrid().addRecordExpandHandler(new RecordExpandHandler() {
				
				@Override
				public void onRecordExpand(RecordExpandEvent event) {
					
	             Minutes minute=new Minutes();
					
					ListGridRecord record = getView().getMeetingPane().getMeetingListGrid().getSelectedRecord();
					Meetings meeting = new Meetings();
					meeting.setId(record.getAttribute(MeetingListGrid.MEETINGS_ID));
					
					minute.setMeeting(meeting);
					
					System.out.println("Meeting Id on minute"+minute.getMeeting().getId());
				
					  dispatcher.execute(new MeetingsAction(MeetingsConstants.RETRIEVE_MEETINGS_MINUTES),
								new AsyncCallback<MeetingsResult>() {
											public void onFailure(Throwable caught) {
												System.out.println("An Error Occurred: " + caught.getMessage());
											}
				
											public void onSuccess(MeetingsResult result) {
												if (result != null) {
													
													//getView().getMeetingPane().getMeetingListGrid();
													//MeetingListGrid.getMeetingMinuteListGrid().addRecordsToGrid(result.getMinutesList());
													getView().getMeetingPane().getMeetingListGrid();
													  MeetingListGrid.getAgendaItemListGrid(); 
													 AgendaItemListGrid.getMeetingMinuteListGrid().addRecordsToGrid(result.
													  getMinutesList());
													 
												} else {
													System.out.println("Meetings list is Empty www");
												}
											}
								});

					
				}
			});
		}
		
		
		private void onRightClickMeetingListGrid() {

			getView().getMeetingPane().getMeetingListGrid().addCellContextClickHandler(new CellContextClickHandler() {

				public void onCellContextClick(CellContextClickEvent event) {
					
					getView().getMeetingPane().getMeetingListGrid().deselectAllRecords();
					getView().getMeetingPane().getMeetingListGrid().selectRecord(event.getRecord());

					Menu menu = new Menu();

					MenuItem editItem = new MenuItem("Edit");
					MenuItem deleteItem = new MenuItem("Delete");
					MenuItem addMinuteItem=new MenuItem("Add Minute");
					MenuItem deleteMinuteItem=new MenuItem("Delete Minute");
					MenuItem editMinuteItem=new MenuItem("Edit Minute");
					

					editItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

						public void onClick(MenuItemClickEvent event) {
							
							onMeetingRecordSelectedToEdit();
						}
					});

					deleteItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

						public void onClick(MenuItemClickEvent event) {

							SC.ask("COMFIRM", "Are You Sure You want to delete the Selected Meeting?", new BooleanCallback() {

								public void execute(Boolean value) {
									if (value == true) {
										
										deleteMeeting();
										
									}

								}
							});

						}
					});
					
					addMinuteItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

						public void onClick(MenuItemClickEvent event) {
							MeetingMinuteFormWindow meetingMinuteFormWindow=new MeetingMinuteFormWindow();
							
							getView().getMeetingPane().getMeetingListGrid();
							if(MeetingListGrid.getAgendaItemListGrid().anySelected()) {
								onsaveMeetingMinute(meetingMinuteFormWindow);
								meetingMinuteFormWindow.show();
							}else {
								SC.say("Select an Agenda Item");
							}

						}
					});
					
					deleteMinuteItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

						public void onClick(MenuItemClickEvent event) {
							
							SC.ask("COMFIRM", "Are You Sure You want to delete the Selected Minute?", new BooleanCallback() {

								public void execute(Boolean value) {
									if (value == true) {
										
										deleteMeetingMinute();
										
									}

								}
							});

						}
					});
					
					editMinuteItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

						public void onClick(MenuItemClickEvent event) {
							
							
						}
					});

					 menu.setItems(editItem, new MenuItemSeparator(),
							  deleteItem,new MenuItemSeparator(),addMinuteItem,new MenuItemSeparator(),deleteMinuteItem,new MenuItemSeparator(),editMinuteItem); 
					 
					 getView().getMeetingPane().getMeetingListGrid().setContextMenu(menu);

				}
			});
		}
		
		private void deleteMeeting() {
			
			ListGridRecord record = getView().getMeetingPane().getMeetingListGrid().getSelectedRecord();
			Meetings meeting = new Meetings();
			meeting.setId(record.getAttribute(MeetingListGrid.MEETINGS_ID));
			
			dispatcher.execute(new MeetingsAction(MeetingsConstants.DELETE_MEETINGS,meeting),
					new AsyncCallback<MeetingsResult>() {
								public void onFailure(Throwable caught) {
									System.out.println("An Error Occurred: " + caught.getMessage());
								}
	
								public void onSuccess(MeetingsResult result) {
									if (result != null) {
										if (result.isReponse()) {
											
											SC.say("SUCCESS", "Successfully Deleted");
											
											getView().getMeetingPane().getMeetingListGrid().addRecordsToGrid(result.getMeetingsList());

										}

									} else {
										System.out.println("Meetings list is Empty");
									}
								}
					});
			
		}
		
		public void onMeetingRecordSelectedToEdit(){
			
			MeetingsFormWindow meetingsFormWindow=new MeetingsFormWindow();
			
			loadCommiteeCombo(meetingsFormWindow);
			loadFinancialYearCombo(meetingsFormWindow);
			loadAgendaCombo(meetingsFormWindow);
			
			meetingsFormWindow.getName().setValue(getView().getMeetingPane().getMeetingListGrid()
					.getSelectedRecord().getAttribute(MeetingListGrid.TITTLE));
			meetingsFormWindow.getVenue().setValue(getView().getMeetingPane().getMeetingListGrid()
					.getSelectedRecord().getAttribute(MeetingListGrid.VENUE));
			meetingsFormWindow.getComment().setValue(getView().getMeetingPane().getMeetingListGrid()
					.getSelectedRecord().getAttribute(MeetingListGrid.COMMENT));
			
			meetingsFormWindow.getAgendaCombo().setValue(getView().getMeetingPane().getMeetingListGrid()
					.getSelectedRecord().getAttribute(MeetingListGrid.AGENDA_ID));
			
			meetingsFormWindow.getCommitteeCombo().setValue(getView().getMeetingPane().getMeetingListGrid()
					.getSelectedRecord().getAttribute(MeetingListGrid.COMMITTE_ID));
			meetingsFormWindow.getFinancialYearCombo().setValue(getView().getMeetingPane().getMeetingListGrid()
					.getSelectedRecord().getAttribute(MeetingListGrid.FINANCIAL_YEAR_ID));
			
			editMeeting(meetingsFormWindow);
			meetingsFormWindow.show();
		 }
		
		private void editMeeting(final MeetingsFormWindow meetingsFormWindow) {
			 
			meetingsFormWindow.getSaveCommitteeButton().addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					Meetings meeting=new Meetings();
					
					meeting.setId(getView().getMeetingPane().getMeetingListGrid()
					.getSelectedRecord().getAttribute(MeetingListGrid.MEETINGS_ID));
					
					meeting.setProcurementRefNo(getView().getMeetingPane().getMeetingListGrid()
					.getSelectedRecord().getAttribute(MeetingListGrid.REF_NO));
					
					Date date=new Date();
					meeting.setDate(date);
					
					meeting.setTitle(meetingsFormWindow.getName().getValueAsString());
					
					meeting.setVenue(meetingsFormWindow.getVenue().getValueAsString());
					
					meeting.setComment(meetingsFormWindow.getComment().getValueAsString());

					
					FinancialYear financialYear=new FinancialYear();
					financialYear.setId(meetingsFormWindow.getFinancialYearCombo().getValueAsString());
					
					meeting.setFinancialYear(financialYear);
					
					Agenda agenda=new Agenda();
					agenda.setId(meetingsFormWindow.getAgendaCombo().getValueAsString());
					
					meeting.setAgenda(agenda);
					
					Committees committee=new Committees();
					committee.setId(meetingsFormWindow.getCommitteeCombo().getValueAsString());
					
					meeting.setCommittee(committee);
					
									
					dispatcher.execute(new MeetingsAction(MeetingsConstants.EDIT_MEETINGS,meeting),
							new AsyncCallback<MeetingsResult>() {
										public void onFailure(Throwable caught) {
											System.out.println("An Error Occurred: " + caught.getMessage());
										}
			
										public void onSuccess(MeetingsResult result) {
											if (result != null) {
												if (result.isReponse()) {
													
													SC.say("SUCCESS", "Successfully Edited");
													
													getView().getMeetingPane().getMeetingListGrid().addRecordsToGrid(result.getMeetingsList());

													meetingsFormWindow.clear();
												}

											} else {
												System.out.println("Meetings list is Empty");
											}
										}
							});
					
					
				}
			});
		}
		
		private void onsaveMeetingMinute(final MeetingMinuteFormWindow meetingMinuteFormWindow) {
			meetingMinuteFormWindow.getSaveMinuteButton().addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
				
					Minutes minute=new Minutes();
					
					minute.setAction(meetingMinuteFormWindow.getAction().getValueAsString());
					minute.setPersonResponsible(meetingMinuteFormWindow.getPersonResponsible().getValueAsString());
					minute.setDeadLine(meetingMinuteFormWindow.getDeadLine().getValueAsDate());
					minute.setComment(meetingMinuteFormWindow.getComment().getValueAsString());
					minute.setStatus(Status.ACTIVE);
					
					AgendaItems agendaItem=new AgendaItems();
					getView().getMeetingPane().getMeetingListGrid();
					agendaItem.setId(MeetingListGrid.getAgendaItemListGrid()
							.getSelectedRecord().getAttribute(AgendaItemListGrid.AGENDA_ITEM_ID));
					
					Meetings meeting=new Meetings();
					meeting.setId(getView().getMeetingPane().getMeetingListGrid()
							.getSelectedRecord().getAttribute(MeetingListGrid.MEETINGS_ID));
					
					minute.setAgendaItem(agendaItem);
					minute.setMeeting(meeting);
					
					Date date=new Date();
					minute.setDateMade(date);
					
					
					dispatcher.execute(new MeetingsAction(minute,MeetingsConstants.SAVE_MEETINGS_MINUTES),
							new AsyncCallback<MeetingsResult>() {
										public void onFailure(Throwable caught) {
											System.out.println("An Error Occurred: " + caught.getMessage());
										}
			
										public void onSuccess(MeetingsResult result) {
											if (result != null) {
												if (result.isReponse()) {
													
													SC.say("SUCCESS", "Successfully Saved");
													
													getView().getMeetingPane().getMeetingListGrid();
													MeetingListGrid.getMeetingMinuteListGrid().addRecordsToGrid(result.getMinutesList());

												}

											} else {
												System.out.println("Minute list is Empty");
											}
										}
							});
					
					
				}
			});
			
		}
		
private void deleteMeetingMinute() {
			
			Minutes minute = new Minutes();
			getView().getMeetingPane().getMeetingListGrid().getSelectedRecord();
			minute.setId(MeetingListGrid.getMeetingMinuteListGrid().getAttribute(MeetingMinuteListGrid.MINUTE_ID));
			
			dispatcher.execute(new MeetingsAction(minute,MeetingsConstants.DELETE_MEETINGS_MINUTES),
					new AsyncCallback<MeetingsResult>() {
								public void onFailure(Throwable caught) {
									System.out.println("An Error Occurred: " + caught.getMessage());
								}
	
								public void onSuccess(MeetingsResult result) {
									if (result != null) {
										if (result.isReponse()) {
											
											SC.say("SUCCESS", "Successfully Deleted");
											
											getView().getMeetingPane().getMeetingListGrid();
											MeetingListGrid.getMeetingMinuteListGrid().addRecordsToGrid(result.getMinutesList());

										}

									} else {
										System.out.println("Meetings list is Empty");
									}
								}
					});
			
		}
		

private void JsonTest() {
	
}
	 
}
