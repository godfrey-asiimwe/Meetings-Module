package com.planetsystems.ui.meeting.meeting_ui.server;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.planetsystems.core.meeting.services.AgendaService;
import com.planetsystems.core.meeting.services.CommitteeService;
import com.planetsystems.model.meetings.Agenda;
import com.planetsystems.model.meetings.Committees;
import com.planetsystems.ui.meeting.meeting_ui.client.util.MeetingsConstants;
import com.planetsystems.ui.meeting.meeting_ui.shared.CommitteeAction;
import com.planetsystems.ui.meeting.meeting_ui.shared.CommitteeActionResult;

public class CommitteeActionActionHandler extends AbstractActionHandler<CommitteeAction, CommitteeActionResult> {
	
	
	@Autowired
	private CommitteeService committeeService;
	
	@Autowired 
	private AgendaService agendaService;

	public CommitteeActionActionHandler() {
		super(CommitteeAction.class);
	}

	public CommitteeActionResult execute(CommitteeAction action, ExecutionContext context) throws ActionException {
		
		if (action.getOperation().equalsIgnoreCase(MeetingsConstants.SAVE_COMMITTEE)) {
			
			boolean saved = committeeService.save(action.getCommittees());
			
			List<Committees> list=committeeService.findAll();
			
			return new CommitteeActionResult(saved,list);
			
		}else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.EDIT_COMMITTEE)) {
			
			boolean saved = committeeService.edit(action.getCommittees());
			
			List<Committees> list=committeeService.findAll();
			
			return new CommitteeActionResult(saved,list);
			
		}else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.RETRIEVE_COMMITTEE)) {

			 boolean saved =false;
			 
			 List<Committees> list =committeeService.findAll() ;
			 
			return new CommitteeActionResult(saved,list);
				
			}else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.DELETE_COMMITTEE)) {
				
				boolean saved = committeeService.delete(action.getCommittees());
				
				List<Committees> list=committeeService.findAll();
				
				return new CommitteeActionResult(saved,list);
				
			}else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.SAVE_AGENDA)) {
				
				boolean saved = agendaService.save(action.getAgenda());
				
				List<Agenda> list=agendaService.findAll();
				
			return new CommitteeActionResult(list,saved);
				
			}else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.EDIT_AGENDA)) {
				
				boolean saved = agendaService.edit(action.getAgenda());
				
				List<Agenda> list=agendaService.findAll();
				
			return new CommitteeActionResult(list,saved);
				
			}else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.DELETE_AGENDA)) {
				
				boolean saved = agendaService.delete(action.getAgenda());
				
				List<Agenda> list=agendaService.findAll();
				
				return new CommitteeActionResult(list,saved);
				
			}else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.RETRIEVE_AGENDA)) {
				
				boolean saved =false;
				
				List<Agenda> list=agendaService.findAll();
				
				return new CommitteeActionResult(list,saved);
				
			}else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.RETRIEVE_AGENDA)) {
				
				boolean saved =false;
				
				Agenda agenda=agendaService.find(action.getAgenda().getId());
				
				return new CommitteeActionResult(saved,agenda);
				
			}
		 else {
			 return null;
		 }
	}

	public void undo(CommitteeAction action, CommitteeActionResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<CommitteeAction> getActionType() {
		return CommitteeAction.class;
	}
}
