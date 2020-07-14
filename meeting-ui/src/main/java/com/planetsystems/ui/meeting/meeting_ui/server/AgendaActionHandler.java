package com.planetsystems.ui.meeting.meeting_ui.server;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.planetsystems.core.meeting.services.AgendaItemService;
import com.planetsystems.core.meeting.services.CommitteeService;
import com.planetsystems.model.meetings.AgendaItems;
import com.planetsystems.model.meetings.CommitteeMember;
import com.planetsystems.ui.meeting.meeting_ui.client.util.MeetingsConstants;
import com.planetsystems.ui.meeting.meeting_ui.shared.AgendaItemAction;
import com.planetsystems.ui.meeting.meeting_ui.shared.AgendaItemActionResult;

public class AgendaActionHandler extends AbstractActionHandler<AgendaItemAction, AgendaItemActionResult> {
	
	@Autowired
	private AgendaItemService agendaItemService;
	
	@Autowired
	private CommitteeService committeeService;
	
	public AgendaActionHandler() {
		super(AgendaItemAction.class);
	}

	public AgendaItemActionResult execute(AgendaItemAction action, ExecutionContext context) throws ActionException {
		
		if (action.getOperation().equalsIgnoreCase(MeetingsConstants.SAVE_AGENDA_ITEM)) {
				
				boolean saved = agendaItemService.save(action.getAgendaItems());
				
				List<AgendaItems> list=agendaItemService.findAgendaItemByAgenda(action.getAgendaItems().getAgenda());	
				return new AgendaItemActionResult(saved,list);
				
			}else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.RETRIEVE_AGENDA_ITEM)) {
				
				boolean saved =false;
				
				List<AgendaItems> list=agendaItemService.findAll();
				
				return new AgendaItemActionResult(saved,list);
				
			}else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.DELETE_AGENDA_ITEM)) {
				
				boolean saved = agendaItemService.delete(action.getAgendaItems());
				
				List<AgendaItems> list=agendaItemService.findAll();
				
				return new AgendaItemActionResult(saved,list);
				
			}else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.EDIT_AGENDA_ITEM)) {
				
				boolean saved = agendaItemService.edit(action.getAgendaItems());
				
				List<AgendaItems> list=agendaItemService.findAll();
				
				return new AgendaItemActionResult(saved,list);
				
			}else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.RETRIEVE_AGENDA_ITEM_BYAGENDA)) {
				
				boolean saved =false;
				
				List<AgendaItems> list=agendaItemService.findAgendaItemByAgenda(action.getAgendaItems().getAgenda());
				
				return new AgendaItemActionResult(saved,list);
				
			}else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.SAVE_COMMITTEE_MEMBER)) {
			
			boolean saved = committeeService.save(action.getCommitteeMember());
			
			List<CommitteeMember> list=committeeService.getCommitteeMemberByCommittee(action.getCommitteeMember().getCommittee());	
			
			return new AgendaItemActionResult(list,saved);
			
		   }else if (action.getOperation().equalsIgnoreCase(MeetingsConstants.RETRIEVE_COMMITTEE_USER)) {
			
			boolean saved = false;
			
			List<CommitteeMember> list=committeeService.getCommitteeMemberByCommittee(action.getCommitteeMember().getCommittee());	
			return new AgendaItemActionResult(list,saved);
			
		  }
		 else {
			 return null;
		 }
	}

	public void undo(AgendaItemAction action, AgendaItemActionResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<AgendaItemAction> getActionType() {
		return AgendaItemAction.class;
	}
}
