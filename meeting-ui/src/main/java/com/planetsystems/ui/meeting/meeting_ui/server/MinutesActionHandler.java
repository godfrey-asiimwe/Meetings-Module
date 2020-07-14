package com.planetsystems.ui.meeting.meeting_ui.server;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.planetsystems.core.meeting.services.AgendaItemService;
import com.planetsystems.core.meeting.services.MeetingsService;
import com.planetsystems.model.meetings.AgendaItems;
import com.planetsystems.model.meetings.Meetings;
import com.planetsystems.model.meetings.Minutes;
import com.planetsystems.ui.meeting.meeting_ui.client.util.MeetingsConstants;
import com.planetsystems.ui.meeting.meeting_ui.shared.MinuteActionResult;
import com.planetsystems.ui.meeting.meeting_ui.shared.MinutesAction;

public class MinutesActionHandler extends AbstractActionHandler<MinutesAction, MinuteActionResult> {
	
	@Autowired
	private MeetingsService meetingsService;
	
	@Autowired
	private AgendaItemService agendaItemService;
	

	public MinutesActionHandler() {
		super(MinutesAction.class);
	}

	public MinuteActionResult execute(MinutesAction action, ExecutionContext context) throws ActionException {
		 if (action.getOperation().equalsIgnoreCase(MeetingsConstants.RETRIEVE_MEETINGS_MINUTES)) {
				
				boolean saved =false;
				
				 List<Minutes> minutesList = new LinkedList<Minutes>();
					List<Minutes> minutesListDTO = new LinkedList<Minutes>();
					
					minutesList = meetingsService.findAllMinutes();
					
					System.out.println("The number of minutes returned"+minutesList.size());
					
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
					
					System.out.println("The number of minutes returned part 2"+minutesListDTO.size());
						
				return new MinuteActionResult(saved,minutesListDTO);
				
			}else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.RETRIEVE_MEETINGS_MINUTES_BYAGENDAITEM)) {
				
				boolean saved =false;
				
				 List<Minutes> minutesList = new LinkedList<Minutes>();
					List<Minutes> minutesListDTO = new LinkedList<Minutes>();
					
					minutesList = meetingsService.findMinutesByMeeting(action.getMinute().getMeeting());
		
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
						
				return new MinuteActionResult(saved,minutesListDTO);
			}else {
			return null;
		}
		
	}

	public void undo(MinutesAction action, MinuteActionResult result, ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<MinutesAction> getActionType() {
		return MinutesAction.class;
	}
	
	
}
