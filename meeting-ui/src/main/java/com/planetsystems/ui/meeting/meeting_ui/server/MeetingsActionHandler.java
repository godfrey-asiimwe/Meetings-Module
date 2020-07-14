package com.planetsystems.ui.meeting.meeting_ui.server;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import java.io.*;
import java.lang.String; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.planetsystems.core.meeting.services.AgendaItemService;
import com.planetsystems.core.meeting.services.AgendaService;
import com.planetsystems.core.meeting.services.CommitteeService;
import com.planetsystems.core.meeting.services.MeetingsService;
import com.planetsystems.core.utils.utils_core.services.FinancialYearService;
import com.planetsystems.model.meetings.Agenda;
import com.planetsystems.model.meetings.AgendaItems;
import com.planetsystems.model.meetings.Committees;
import com.planetsystems.model.meetings.Meetings;
import com.planetsystems.model.meetings.Minutes;
import com.planetsystems.model.utils.utils_model.FinancialYear;
import com.planetsystems.ui.meeting.meeting_ui.client.util.MeetingsConstants;
import com.planetsystems.ui.meeting.meeting_ui.shared.MeetingsAction;
import com.planetsystems.ui.meeting.meeting_ui.shared.MeetingsResult;

public class MeetingsActionHandler extends AbstractActionHandler<MeetingsAction, MeetingsResult> {
	
	@Autowired
	private MeetingsService meetingsService;
	
	@Autowired
	private FinancialYearService financialYearService;
	
	@Autowired
	private AgendaService agendaService;
	
	@Autowired
	private CommitteeService committeeService;
	
	@Autowired
	private AgendaItemService agendaItemService;
	

	public MeetingsActionHandler() {
		super(MeetingsAction.class);
	}

	public MeetingsResult execute(MeetingsAction action, ExecutionContext context) throws ActionException {
		
		if (action.getOperationMode().equalsIgnoreCase(MeetingsConstants.SAVE_MEETINGS)) {
			
			boolean saved = meetingsService.save(action.getMeetings());
			
			 List<Meetings> meetingsList = new LinkedList<Meetings>();
				List<Meetings> meetingsListDTO = new LinkedList<Meetings>();
				
				meetingsList = meetingsService.findAll();
				
				for(Meetings meeting:meetingsList) {
					Meetings meetingDTO=new Meetings();
					meetingDTO.setId(meeting.getId());
					meetingDTO.setTitle(meeting.getTitle());
					meetingDTO.setVenue(meeting.getVenue());
					meetingDTO.setProcurementRefNo(meeting.getProcurementRefNo());
					meetingDTO.setStatus(meeting.getStatus());
					meetingDTO.setDate(meeting.getDate());
					meetingDTO.setComment(meeting.getComment());
					
					Agenda agenda=new Agenda();
					agenda=agendaService.find(meeting.getAgenda().getId());
					
					meetingDTO.setAgenda(agenda);
					
					Committees committee=new Committees();
					committee=committeeService.find(meeting.getCommittee().getId());
					
					meetingDTO.setCommittee(committee);
					
					FinancialYear financialYear=new FinancialYear();
					financialYear=financialYearService.find(meeting.getFinancialYear().getId());
					
					meetingDTO.setFinancialYear(financialYear);
					
					meetingsListDTO.add(meetingDTO);
					
					
				}
					
			return new MeetingsResult(saved, meetingsListDTO);
		}

		else if (action.getOperationMode().equalsIgnoreCase(MeetingsConstants.EDIT_MEETINGS)) {
			boolean saved = meetingsService.edit(action.getMeetings());
			
			 List<Meetings> meetingsList = new LinkedList<Meetings>();
				List<Meetings> meetingsListDTO = new LinkedList<Meetings>();
				
				meetingsList = meetingsService.findAll();
				
				for(Meetings meeting:meetingsList) {
					Meetings meetingDTO=new Meetings();
					meetingDTO.setId(meeting.getId());
					meetingDTO.setTitle(meeting.getTitle());
					meetingDTO.setVenue(meeting.getVenue());
					meetingDTO.setProcurementRefNo(meeting.getProcurementRefNo());
					meetingDTO.setStatus(meeting.getStatus());
					meetingDTO.setDate(meeting.getDate());
					meetingDTO.setComment(meeting.getComment());
					
					Agenda agenda=new Agenda();
					agenda=agendaService.find(meeting.getAgenda().getId());
					
					meetingDTO.setAgenda(agenda);
					
					Committees committee=new Committees();
					committee=committeeService.find(meeting.getCommittee().getId());
					
					meetingDTO.setCommittee(committee);
					
					FinancialYear financialYear=new FinancialYear();
					financialYear=financialYearService.find(meeting.getFinancialYear().getId());
					
					meetingDTO.setFinancialYear(financialYear);
					
					meetingsListDTO.add(meetingDTO);
					
				}
					
			return new MeetingsResult(saved, meetingsListDTO);
		}

		else if (action.getOperationMode().equalsIgnoreCase(MeetingsConstants.DELETE_MEETINGS)) {
			boolean saved = meetingsService.delete(action.getMeetings());
			
			 List<Meetings> meetingsList = new LinkedList<Meetings>();
				List<Meetings> meetingsListDTO = new LinkedList<Meetings>();
				
				meetingsList = meetingsService.findAll();
				
				for(Meetings meeting:meetingsList) {
					Meetings meetingDTO=new Meetings();
					meetingDTO.setId(meeting.getId());
					meetingDTO.setTitle(meeting.getTitle());
					meetingDTO.setVenue(meeting.getVenue());
					meetingDTO.setProcurementRefNo(meeting.getProcurementRefNo());
					meetingDTO.setStatus(meeting.getStatus());
					meetingDTO.setDate(meeting.getDate());
					meetingDTO.setComment(meeting.getComment());
					
					Agenda agenda=new Agenda();
					agenda=agendaService.find(meeting.getAgenda().getId());
					
					meetingDTO.setAgenda(agenda);
					
					Committees committee=new Committees();
					committee=committeeService.find(meeting.getCommittee().getId());
					
					meetingDTO.setCommittee(committee);
					
					FinancialYear financialYear=new FinancialYear();
					financialYear=financialYearService.find(meeting.getFinancialYear().getId());
					
					meetingDTO.setFinancialYear(financialYear);
					
					meetingsListDTO.add(meetingDTO);
					
				}
					
			return new MeetingsResult(saved, meetingsListDTO);
		}

		else if (action.getOperationMode().equalsIgnoreCase(MeetingsConstants.RETRIEVE_MEETINGS)) {
			
            boolean saved = false;
			
            List<Meetings> meetingsList = new LinkedList<Meetings>();
			List<Meetings> meetingsListDTO = new LinkedList<Meetings>();
			
			meetingsList = meetingsService.findAll();
			
			for(Meetings meeting:meetingsList) {
				Meetings meetingDTO=new Meetings();
				meetingDTO.setId(meeting.getId());
				meetingDTO.setTitle(meeting.getTitle());
				meetingDTO.setVenue(meeting.getVenue());
				meetingDTO.setProcurementRefNo(meeting.getProcurementRefNo());
				meetingDTO.setStatus(meeting.getStatus());
				meetingDTO.setDate(meeting.getDate());
				meetingDTO.setComment(meeting.getComment());
				
				Agenda agenda=new Agenda();
				agenda=agendaService.find(meeting.getAgenda().getId());
				
				meetingDTO.setAgenda(agenda);
				
				Committees committee=new Committees();
				committee=committeeService.find(meeting.getCommittee().getId());
				
				meetingDTO.setCommittee(committee);
				
				FinancialYear financialYear=new FinancialYear();
				financialYear=financialYearService.find(meeting.getFinancialYear().getId());
				
				
				meetingDTO.setFinancialYear(financialYear);
				
				meetingsListDTO.add(meetingDTO);
				
				
				String folder="H:\\";
				
				FileWriter file;
				try {
				
					
				    // create book object
					file = new FileWriter(folder + "" + meetingDTO.getId() + ".json");
				    // convert book object to JSON
				    String json = new Gson().toJson(meetingDTO);

				    // print JSON string
				    System.out.println(json);
				    file.write(json);

				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				} 
				
			}
					
			return new MeetingsResult(saved, meetingsListDTO);
			
		}else if (action.getOperationMode().equalsIgnoreCase(MeetingsConstants.SAVE_MEETINGS_MINUTES)) {
				
				boolean saved = meetingsService.save(action.getMinutes());
				
				 List<Minutes> minutesList = new LinkedList<Minutes>();
					List<Minutes> minutesListDTO = new LinkedList<Minutes>();
					
					Meetings meeting=new Meetings();
					meeting=meetingsService.find(action.getMinutes().getMeeting().getId());
					
					minutesList = meetingsService.findMinutesByMeeting(meeting);
					
					for(Minutes minutes:minutesList) {
						Minutes minutesDTO=new Minutes();
						
						minutesDTO.setId(minutes.getId());
						minutesDTO.setPersonResponsible(minutes.getPersonResponsible());
						minutesDTO.setAction(minutes.getAction());
						minutesDTO.setComment(minutes.getComment());
						minutesDTO.setDeadLine(minutes.getDeadLine());
						minutesDTO.setStatus(minutes.getStatus());

						minutesDTO.setDateMade(minutes.getDateMade());
						
						AgendaItems agendaItems=new AgendaItems();
						agendaItems=agendaItemService.find(minutes.getAgendaItem().getId());
						
						Meetings meeting2=new Meetings();
						meeting2=meetingsService.find(minutes.getMeeting().getId());
						
						minutesDTO.setAgendaItem(agendaItems);
						minutesDTO.setMeeting(meeting2);
						
						minutesListDTO.add(minutesDTO);
						
					}
						
				return new MeetingsResult(minutesListDTO,saved);
			}else if (action.getOperationMode().equalsIgnoreCase(MeetingsConstants.EDIT_MEETINGS_MINUTES)) {
				
				boolean saved = meetingsService.edit(action.getMinutes());
				
				 List<Minutes> minutesList = new LinkedList<Minutes>();
					List<Minutes> minutesListDTO = new LinkedList<Minutes>();
					
					minutesList = meetingsService.findAllMinutes();
					
					for(Minutes minutes:minutesList) {
						Minutes minutesDTO=new Minutes();
						
						minutesDTO.setId(minutes.getId());
						minutesDTO.setPersonResponsible(minutes.getPersonResponsible());
						minutesDTO.setAction(minutes.getAction());
						minutesDTO.setComment(minutes.getComment());
						minutesDTO.setDeadLine(minutes.getDeadLine());
						minutesDTO.setStatus(minutes.getStatus());

						minutesDTO.setDateMade(minutes.getDateMade());
						
						AgendaItems agendaItems=new AgendaItems();
						agendaItems=agendaItemService.find(minutes.getAgendaItem().getId());
						
						Meetings meeting=new Meetings();
						meeting=meetingsService.find(minutes.getMeeting().getId());
						
						minutesDTO.setAgendaItem(agendaItems);
						minutesDTO.setMeeting(meeting);
						
						minutesListDTO.add(minutesDTO);
						
					}
						
				return new MeetingsResult(minutesListDTO,saved);
			}else if (action.getOperationMode().equalsIgnoreCase(MeetingsConstants.DELETE_MEETINGS_MINUTES)) {
				
				boolean saved = meetingsService.delete(action.getMinutes());
				
				 List<Minutes> minutesList = new LinkedList<Minutes>();
					List<Minutes> minutesListDTO = new LinkedList<Minutes>();
					
					minutesList = meetingsService.findAllMinutes();
					
					for(Minutes minutes:minutesList) {
						Minutes minutesDTO=new Minutes();
						
						minutesDTO.setId(minutes.getId());
						minutesDTO.setPersonResponsible(minutes.getPersonResponsible());
						minutesDTO.setAction(minutes.getAction());
						minutesDTO.setComment(minutes.getComment());
						minutesDTO.setDeadLine(minutes.getDeadLine());
						minutesDTO.setStatus(minutes.getStatus());

						minutesDTO.setDateMade(minutes.getDateMade());
						
						AgendaItems agendaItems=new AgendaItems();
						agendaItems=agendaItemService.find(minutes.getAgendaItem().getId());
						
						Meetings meeting=new Meetings();
						meeting=meetingsService.find(minutes.getMeeting().getId());
						
						minutesDTO.setAgendaItem(agendaItems);
						minutesDTO.setMeeting(meeting);
						
						minutesListDTO.add(minutesDTO);
						
					}
						
				return new MeetingsResult(minutesListDTO,saved);
			}else if (action.getOperationMode().equalsIgnoreCase(MeetingsConstants.RETRIEVE_MEETINGS_MINUTES)) {
				
				boolean saved =false;
				
				 List<Minutes> minutesList = new LinkedList<Minutes>();
					List<Minutes> minutesListDTO = new LinkedList<Minutes>();
					
					minutesList = meetingsService.findAllMinutes();
					
					for(Minutes minutes:minutesList) {
						Minutes minutesDTO=new Minutes();
						
						minutesDTO.setId(minutes.getId());
						minutesDTO.setPersonResponsible(minutes.getPersonResponsible());
						minutesDTO.setAction(minutes.getAction());
						minutesDTO.setComment(minutes.getComment());
						minutesDTO.setDeadLine(minutes.getDeadLine());
						minutesDTO.setStatus(minutes.getStatus());
						minutesDTO.setDateMade(minutes.getDateMade());
						
						minutesListDTO.add(minutesDTO);
						
					}
						
				return new MeetingsResult(minutesListDTO,saved);
			}else if (action.getOperationMode().equalsIgnoreCase(MeetingsConstants.RETRIEVE_MEETINGS_MINUTES_BYAGENDAITEM)) {
				
				boolean saved =false;
				
				 List<Minutes> minutesList = new LinkedList<Minutes>();
					List<Minutes> minutesListDTO = new LinkedList<Minutes>();
					
					minutesList = meetingsService.findMinutesByAgendaItem(action.getMinutes().getAgendaItem());
					
					for(Minutes minutes:minutesList) {
						Minutes minutesDTO=new Minutes();
						
						minutesDTO.setId(minutes.getId());
						minutesDTO.setPersonResponsible(minutes.getPersonResponsible());
						minutesDTO.setAction(minutes.getAction());
						minutesDTO.setComment(minutes.getComment());
						minutesDTO.setDeadLine(minutes.getDeadLine());
						minutesDTO.setStatus(minutes.getStatus());
						minutesDTO.setDateMade(minutes.getDateMade());
						
						System.out.println("Person responsible"+minutesDTO.getPersonResponsible());
						
						minutesListDTO.add(minutesDTO);
						
						System.out.println("minutes by agendaItem Id"+minutesListDTO.size());
						
					}
						
				return new MeetingsResult(minutesListDTO,saved);
			}else {
			return null;
		}
	}

	public void undo(MeetingsAction action, MeetingsResult result, ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<MeetingsAction> getActionType() {
		return MeetingsAction.class;
	}
	
	public List<Meetings> getMeetingList() {
		
		 List<Meetings> meetingsList = new LinkedList<Meetings>();
			List<Meetings> meetingsListDTO = new LinkedList<Meetings>();
			
			meetingsList = meetingsService.findAll();
			
			for(Meetings meeting:meetingsList) {
				
				Meetings meetingDTO=new Meetings();
				meetingDTO.setId(meeting.getId());
				meetingDTO.setTitle(meeting.getTitle());
				meetingDTO.setVenue(meeting.getVenue());
				meetingDTO.setProcurementRefNo(meeting.getProcurementRefNo());
				meetingDTO.setStatus(meeting.getStatus());
				meetingDTO.setDate(meeting.getDate());
				meetingDTO.setComment(meeting.getComment());
				
				Agenda agenda=new Agenda();
				agenda=agendaService.find(meeting.getAgenda().getId());
				
				meetingDTO.setAgenda(agenda);
				
				Committees committee=new Committees();
				committee=committeeService.find(meeting.getCommittee().getId());
				
				meetingDTO.setCommittee(committee);
				
				FinancialYear financialYear=new FinancialYear();
				financialYear=financialYearService.find(meeting.getFinancialYear().getId());
				
				
				meetingDTO.setFinancialYear(financialYear);
				
				meetingsListDTO.add(meetingDTO);
				
			}
			return meetingsListDTO;
	}
	
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
			try {
				   
				List<Meetings> meetingsList = new LinkedList<Meetings>();
			    
				   JSONObject responseObj = new JSONObject();
				    
				   List<JSONObject> productObjects = new LinkedList<JSONObject>();
				    
				   for (Meetings product : meetingsList) {
				     
				    JSONObject productObj = new JSONObject();
				     
				    productObj.put("name", product.getId());
				    productObj.put("company", product.getTitle());
				    productObj.put("serialNumber", product.getVenue());
				    
				    productObjects.add(productObj);
				    
				   }
				    
				   responseObj.put("products", productObjects);
				   
				   System.out.println(responseObj.names());
				    
				   PrintWriter writer =resp.getWriter();
				   writer.write(responseObj.toString());
				   writer.flush();
				    
				  } 
				  catch (Exception e) {   
				   e.printStackTrace();
				   throw new ServletException(e);
				  }
				  
		 
	 }
}
