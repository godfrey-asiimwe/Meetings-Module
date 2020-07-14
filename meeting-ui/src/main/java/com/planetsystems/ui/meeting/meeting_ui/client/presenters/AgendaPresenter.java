/**
 * 
 */
package com.planetsystems.ui.meeting.meeting_ui.client.presenters;

import java.util.Date;
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
import com.planetsystems.ui.meeting.meeting_ui.client.place.NameTokens;
import com.planetsystems.ui.meeting.meeting_ui.client.util.MeetingsConstants;
import com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids.AgendaItemListGrid;
import com.planetsystems.ui.meeting.meeting_ui.client.views.listgrids.AgendaListGrid;
import com.planetsystems.ui.meeting.meeting_ui.client.views.panes.AgendaItemPane;
import com.planetsystems.ui.meeting.meeting_ui.client.views.panes.AgendaPane;
import com.planetsystems.ui.meeting.meeting_ui.client.views.windows.AgendaFormWindow;
import com.planetsystems.ui.meeting.meeting_ui.client.views.windows.AgendaItemFormWindow;
import com.planetsystems.ui.meeting.meeting_ui.shared.AgendaItemAction;
import com.planetsystems.ui.meeting.meeting_ui.shared.AgendaItemActionResult;
import com.planetsystems.ui.meeting.meeting_ui.shared.CommitteeAction;
import com.planetsystems.ui.meeting.meeting_ui.shared.CommitteeActionResult;
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



public class AgendaPresenter extends Presenter<AgendaPresenter.MyView, AgendaPresenter.MyProxy>{

	final PlaceManager placeManager;
	final DispatchAsync dispatcher;

	/**
	 * @param eventBus
	 * @param view
	 * @param proxy
	 */
	@Inject
	public AgendaPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);

		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}

	
	@ProxyCodeSplit
	@NameToken(NameTokens.agenda)
	public interface MyProxy extends Proxy<AgendaPresenter>, Place {

	}

	public interface MyView extends View {

		public AgendaPane getAgendaPane();
		public AgendaFormWindow getAgendaFormWindow();
	}

	@Override
	protected void onBind() {
		
		super.onBind();
		
		onNewButtonClicked();
		retrieveAgendas();
		deleteAgendaButtonCliked();
		onRightClickAgendaListGrid();
		//onRightClikAgendaItemListGrid();
		onAgendaListGridExpanded();
		onDeleteAgendaItemButtonClicked();

	}

	private void onNewButtonClicked() {
		getView().getAgendaPane().getAddNewbutton().addClickHandler(new ClickHandler() {
					
					public void onClick(ClickEvent event) {
						
						AgendaFormWindow agendaFormWindow=new AgendaFormWindow();

						onSaveAgendaButtonClicked(agendaFormWindow);
						agendaFormWindow.show();
					}

					
				});

		
	}
	
	
	public void ClearControl() {
	
	}
	
	private void onDeleteAgendaItemButtonClicked() {
		getView().getAgendaPane().getAgendaListGrid().getDeleteButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			
				
				deleteAgendaItem();
				
				
			}
		});
	}
	
	private void onSaveAgendaButtonClicked(final AgendaFormWindow agendaFormWindow) {
			
		agendaFormWindow.getSaveAgendaItemButton().addClickHandler(new ClickHandler() {
	        	
				public void onClick(ClickEvent event) {
					
					Agenda agenda=new Agenda();
					
					agenda.setName(agendaFormWindow.getName().getValueAsString());
					agenda.setDescription(agendaFormWindow.getDSC().getValueAsString());
					
					Date date=new Date();
					agenda.setDateOfCreation(date);
				
					dispatcher.execute(new CommitteeAction(MeetingsConstants.SAVE_AGENDA,agenda),
					new AsyncCallback<CommitteeActionResult>() {
								public void onFailure(Throwable caught) {
									System.out.println("An Error Occurred: " + caught.getMessage());
								}
	
								public void onSuccess(CommitteeActionResult result) {
									if (result != null) {
										if (result.isReponse()) {
											
											SC.say("SUCCESS", "Successfully Saved");
											
											agendaFormWindow.clear();
											getView().getAgendaPane().getAgendaListGrid().addRecordsToGrid(result.getAgendaList());
										}

									} else {
										System.out.println("onSaveAgenda list is Empty");
									}
								}
					});
				}
			});
		
	}
	
	public void onAgendaRecordSelectedToEdit(){
		
		AgendaFormWindow agendaFormWindow=new AgendaFormWindow();
	    
		agendaFormWindow.getName().setValue(getView().getAgendaPane().
				getAgendaListGrid().getSelectedRecord().getAttribute(AgendaListGrid.AGENDA_NAME));
		
		agendaFormWindow.getDSC().setValue(getView().getAgendaPane().
				getAgendaListGrid().getSelectedRecord().getAttribute(AgendaListGrid.AGENDA_DESC));
			
	    onEditAgenda(agendaFormWindow);
		
	    agendaFormWindow.show();
	 }
	
	private void onRightClikAgendaItemListGrid() {
		
		getView().getAgendaPane().getAgendaListGrid().getAgendaItemListGrid().addCellContextClickHandler(new CellContextClickHandler() {

			public void onCellContextClick(CellContextClickEvent event) {
				
				getView().getAgendaPane().getAgendaListGrid().getAgendaItemListGrid().deselectAllRecords();
				getView().getAgendaPane().getAgendaListGrid().getAgendaItemListGrid().selectRecord(event.getRecord());

				Menu menu = new Menu();

				MenuItem editItem = new MenuItem("Edit Item");
				MenuItem deleteItem = new MenuItem("Delete Item");
				MenuItem addAgendaItem=new MenuItem("Add Item");
				MenuItem deleteAgendaItem=new MenuItem("Delete Item");

				editItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					public void onClick(MenuItemClickEvent event) {
						
						onAgendaItemRecordSelectedToEdit();
					}
				});

				deleteItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					public void onClick(MenuItemClickEvent event) {

						SC.ask("COMFIRM", "Are You Sure You want to delete the Selected Agenda?", new BooleanCallback() {

							public void execute(Boolean value) {
								if (value == true) {
									
									deleteAgenda();
									
								}

							}
						});

					}
				});
				
				addAgendaItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					public void onClick(MenuItemClickEvent event) {

						AgendaItemFormWindow agendaItemFormWindow=new AgendaItemFormWindow();
						onSaveAgendaItemButtonClicked(agendaItemFormWindow);
						agendaItemFormWindow.show();

					}
				});
				
				deleteAgendaItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					public void onClick(MenuItemClickEvent event) {

						deleteAgendaItem();

					}
				});


				 menu.setItems(editItem, new MenuItemSeparator(),
				  deleteItem,new MenuItemSeparator(),addAgendaItem,new MenuItemSeparator(),deleteAgendaItem); 
				 
				 getView().getAgendaPane().getAgendaListGrid().getAgendaItemListGrid().setContextMenu(menu);

			}
		});
	}
	
	
	
	private void onRightClickAgendaListGrid() {

		getView().getAgendaPane().getAgendaListGrid().addCellContextClickHandler(new CellContextClickHandler() {

			public void onCellContextClick(CellContextClickEvent event) {
				
				getView().getAgendaPane().getAgendaListGrid().deselectAllRecords();
				getView().getAgendaPane().getAgendaListGrid().selectRecord(event.getRecord());

				Menu menu = new Menu();

				MenuItem editItem = new MenuItem("Edit");
				MenuItem deleteItem = new MenuItem("Delete");
				MenuItem addAgendaItem=new MenuItem("Add Item");
				MenuItem deleteAgendaItem=new MenuItem("Delete Item");
				MenuItem editAgendaItem=new MenuItem("Edit Item");
				
				
				/* MenuItem markAsClearedItem = new MenuItem("Mark As Approved"); */

				editItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					public void onClick(MenuItemClickEvent event) {
						
						onAgendaRecordSelectedToEdit();
					}
				});

				deleteItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					public void onClick(MenuItemClickEvent event) {

						SC.ask("COMFIRM", "Are You Sure You want to delete the Selected Agenda?", new BooleanCallback() {

							public void execute(Boolean value) {
								if (value == true) {
									
									deleteAgenda();
									
								}

							}
						});

					}
				});
				
				addAgendaItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					public void onClick(MenuItemClickEvent event) {

						AgendaItemFormWindow agendaItemFormWindow=new AgendaItemFormWindow();
						onSaveAgendaItemButtonClicked(agendaItemFormWindow);
						agendaItemFormWindow.show();

					}
				});
				
				deleteAgendaItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					public void onClick(MenuItemClickEvent event) {
						
						SC.ask("COMFIRM", "Are You Sure You want to delete the Selected Agenda Item?", new BooleanCallback() {

							public void execute(Boolean value) {
								if (value == true) {
									
									deleteAgendaItem();
									
								}

							}
						});

					}
				});
				
				editAgendaItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					public void onClick(MenuItemClickEvent event) {
						
						onAgendaItemRecordSelectedToEdit();
					}
				});

				 menu.setItems(editItem, new MenuItemSeparator(),
						  deleteItem,new MenuItemSeparator(),addAgendaItem,new MenuItemSeparator(),deleteAgendaItem,new MenuItemSeparator(),editAgendaItem); 
				 
				getView().getAgendaPane().getAgendaListGrid().setContextMenu(menu);

			}
		});
	}
	
	
	private void onSaveAgendaItemButtonClicked(final AgendaItemFormWindow agendaItemFormWindow) {
		
		agendaItemFormWindow.getSaveAgendaItemButton().addClickHandler(new ClickHandler() {
	        	
				public void onClick(ClickEvent event) {
					
					AgendaItems agendaItem=new AgendaItems();
					agendaItem.setAction(agendaItemFormWindow.getAction().getValueAsString());
					agendaItem.setRank(agendaItemFormWindow.getRank().getValueAsString());
					agendaItem.setComment(agendaItemFormWindow.getComment().getValueAsString());
					
					Agenda agenda=new Agenda();
					agenda.setId(getView().getAgendaPane().getAgendaListGrid().getSelectedRecord()
							.getAttribute(AgendaListGrid.AGENDA_ID));
					agendaItem.setAgenda(agenda);
					
					Date date=new Date();
					agendaItem.setTime(date);
				
					
					
					dispatcher.execute(new AgendaItemAction(MeetingsConstants.SAVE_AGENDA_ITEM,agendaItem),
					new AsyncCallback<AgendaItemActionResult>() {
								public void onFailure(Throwable caught) {
									System.out.println("An Error Occurred: " + caught.getMessage());
								}
	
								public void onSuccess(AgendaItemActionResult result) {
									if (result != null) {
										if (result.isReponse()) {
											
											SC.say("SUCCESS", "Successfully Saved");
											
											getView().getAgendaPane().getAgendaListGrid().getAgendaItemListGrid().addRecordsToGrid(result.getAgendaItemList());
										}

									} else {
										System.out.println("AgendaItem list is Empty");
									}
								}
					});
				}
			});
		
	}
	
		
	private void onEditAgenda(final AgendaFormWindow agendaFormWindow) {
		
		agendaFormWindow.getSaveAgendaItemButton().addClickHandler(new ClickHandler() {
        	
			public void onClick(ClickEvent event) {
				
				Agenda agenda=new Agenda();
				
				agenda.setId(getView().getAgendaPane().
				getAgendaListGrid().getSelectedRecord().getAttribute(AgendaListGrid.AGENDA_ID));
				
				agenda.setName(agendaFormWindow.getName().getValueAsString());
				agenda.setDescription(agendaFormWindow.getDSC().getValueAsString());
			
			
				dispatcher.execute(new CommitteeAction(MeetingsConstants.EDIT_AGENDA,agenda),
				new AsyncCallback<CommitteeActionResult>() {
							public void onFailure(Throwable caught) {
								System.out.println("An Error Occurred: " + caught.getMessage());
							}

							public void onSuccess(CommitteeActionResult result) {
								if (result != null) {
									if (result.isReponse()) {
										
										SC.say("SUCCESS", "Successfully Editted");
										
										agendaFormWindow.clear();
										getView().getAgendaPane().getAgendaListGrid().addRecordsToGrid(result.getAgendaList());
									}

								} else {
									System.out.println("onSaveAgenda list is Empty");
								}
							}
				});
			}
		});
		
	}

	private void retrieveAgendas() {
		
		dispatcher.execute(new CommitteeAction(MeetingsConstants.RETRIEVE_AGENDA),
				new AsyncCallback<CommitteeActionResult>() {
							public void onFailure(Throwable caught) {
								System.out.println("An Error Occurred: " + caught.getMessage());
							}

							public void onSuccess(CommitteeActionResult result) {
								if (result != null) {
									
									getView().getAgendaPane().getAgendaListGrid().addRecordsToGrid(result.getAgendaList());
								} else {
									System.out.println("Agenda list is Empty");
								}
							}
				});
		
	}
	
	private void deleteAgenda() {
		
		ListGridRecord record = getView().getAgendaPane().getAgendaListGrid().getSelectedRecord();
		Agenda agenda = new Agenda();
		agenda.setId(record.getAttribute(AgendaListGrid.AGENDA_ID));
		
		dispatcher.execute(new CommitteeAction(MeetingsConstants.DELETE_AGENDA,agenda),
				new AsyncCallback<CommitteeActionResult>() {
							public void onFailure(Throwable caught) {
								System.out.println("An Error Occurred: " + caught.getMessage());
							}

							public void onSuccess(CommitteeActionResult result) {
								if (result != null) {
									
									SC.say("SUCCESS", "Successfully Deleted");
									
									getView().getAgendaPane().getAgendaListGrid().addRecordsToGrid(result.getAgendaList());
								} else {
									System.out.println("Agenda list is Empty");
								}
							}
				});
		
	}
	
public void onAgendaItemRecordSelectedToEdit(){
		
		AgendaItemFormWindow agendaItemFormWindow=new AgendaItemFormWindow();
	    
		agendaItemFormWindow.getRank().setValue(getView().getAgendaPane().
				getAgendaListGrid().getAgendaItemListGrid().getSelectedRecord().getAttribute(AgendaItemListGrid.AGENDA_ITEM_RANK));
		
		agendaItemFormWindow.getAction().setValue(getView().getAgendaPane().
				getAgendaListGrid().getAgendaItemListGrid().getSelectedRecord().getAttribute(AgendaItemListGrid.AGENDA_ITEM_ACTION));
		
		
		agendaItemFormWindow.getComment().setValue(getView().getAgendaPane().
				getAgendaListGrid().getAgendaItemListGrid().getSelectedRecord().getAttribute(AgendaItemListGrid.AGENDA_ITEM_COMMENT));
		
		agendaItemFormWindow.getAgenda().setValue(getView().getAgendaPane().
				getAgendaListGrid().getAgendaItemListGrid().getSelectedRecord().getAttribute(AgendaItemListGrid.AGENDA_ITEM_ID));

		editAgendaitem(agendaItemFormWindow);
	    agendaItemFormWindow.show();
	 }
	
	private void editAgendaitem(final AgendaItemFormWindow agendaItemFormWindow) {
		
	agendaItemFormWindow.getSaveAgendaItemButton().addClickHandler(new ClickHandler() {
        	
			public void onClick(ClickEvent event) {
				
				
				AgendaItems agendaItem=new AgendaItems();
				agendaItem.setId(getView().getAgendaPane().
						getAgendaListGrid().getAgendaItemListGrid().getSelectedRecord().getAttribute(AgendaItemListGrid.AGENDA_ITEM_ID));
				agendaItem.setAction(agendaItemFormWindow.getAction().getValueAsString());
				agendaItem.setRank(agendaItemFormWindow.getRank().getValueAsString());
				agendaItem.setComment(agendaItemFormWindow.getComment().getValueAsString());
				
	
				Agenda agenda=new Agenda();
				agenda.setId(getView().getAgendaPane().getAgendaListGrid().getSelectedRecord()
						.getAttribute(AgendaListGrid.AGENDA_ID));
				agendaItem.setAgenda(agenda);
				
				Date date=new Date();
				agendaItem.setTime(date);
				
				
				dispatcher.execute(new AgendaItemAction(MeetingsConstants.EDIT_AGENDA_ITEM,agendaItem),
				new AsyncCallback<AgendaItemActionResult>() {
							public void onFailure(Throwable caught) {
								System.out.println("An Error Occurred: " + caught.getMessage());
							}

							public void onSuccess(AgendaItemActionResult result) {
								if (result != null) {
									if (result.isReponse()) {
										
										SC.say("SUCCESS", "Successfully Saved");
										agendaItemFormWindow.clear();
										getView().getAgendaPane().getAgendaListGrid().getAgendaItemListGrid().addRecordsToGrid(result.getAgendaItemList());
									}

								} else {
									System.out.println("onretrieve agendaItems list is Empty");
								}
							}
				});
			}
		});
	}
	
	private void deleteAgendaItem() {
		
		ListGridRecord record = getView().getAgendaPane().getAgendaListGrid().getAgendaItemListGrid().getSelectedRecord();
		AgendaItems agendaItem=new AgendaItems();
		agendaItem.setId(record.getAttribute(AgendaItemListGrid.AGENDA_ITEM_ID));

		
		dispatcher.execute(new AgendaItemAction(MeetingsConstants.DELETE_AGENDA_ITEM,agendaItem),
				new AsyncCallback<AgendaItemActionResult>() {
							public void onFailure(Throwable caught) {
								
								System.out.println("An Error Occurred: " + caught.getMessage());
								
							}

							public void onSuccess(AgendaItemActionResult result) {
								if (result != null) {
									
									SC.say("SUCCESS", "Successfully Deleted");
									
									getView().getAgendaPane().getAgendaListGrid().getAgendaItemListGrid().addRecordsToGrid(result.getAgendaItemList());
									
								} else {
									
									System.out.println("AgendaItem list is Empty");
									
								}
							}
				});
	}
		
	
	private void deleteAgendaButtonCliked() {
		
		getView().getAgendaPane().deleteAgenda().addClickHandler(new ClickHandler() {
        	
			public void onClick(ClickEvent event) {
				deleteAgenda();
			}

		});
		
	}
	
	private void onAgendaListGridExpanded() {
		
		getView().getAgendaPane().getAgendaListGrid().addRecordExpandHandler(new RecordExpandHandler() {
			
			@Override
			public void onRecordExpand(RecordExpandEvent event) {
				
				
				AgendaItems agendaItem=new AgendaItems();
				
				Agenda agenda=new Agenda();
				
				agenda.setId(getView().getAgendaPane().getAgendaListGrid().getSelectedRecord()
						.getAttribute(AgendaListGrid.AGENDA_ID));
				
				agendaItem.setAgenda(agenda);
				
				
				dispatcher.execute(new AgendaItemAction(MeetingsConstants.RETRIEVE_AGENDA_ITEM_BYAGENDA,agendaItem),
						new AsyncCallback<AgendaItemActionResult>() {
									public void onFailure(Throwable caught) {
										System.out.println("An Error Occurred: " + caught.getMessage());
									}

									public void onSuccess(AgendaItemActionResult result) {
										if (result != null) {
											
											getView().getAgendaPane().getAgendaListGrid().getAgendaItemListGrid().addRecordsToGrid(result.getAgendaItemList());
										} else {
											System.out.println("Committee list is Empty");
										}
									}
						});

				onDeleteAgendaItemButtonClicked();
				
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
