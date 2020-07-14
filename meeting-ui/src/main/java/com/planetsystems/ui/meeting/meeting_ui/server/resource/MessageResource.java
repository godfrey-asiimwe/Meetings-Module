package com.planetsystems.ui.meeting.meeting_ui.server.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.planetsystems.ui.meeting.meeting_ui.server.Message;
import com.planetsystems.ui.meeting.meeting_ui.server.service.MessageService;


@Path("/testing")
public class MessageResource {
	

	MessageService messageService=new MessageService();
	

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public List<Message> getMessages() {
		return messageService.getAllMessages();
	}
	

	  @GET
	  @Path("/{messageId}")
	  @Produces(MediaType.TEXT_PLAIN) 
	  public String getMessage(@PathParam("messageId") long messageId) { return "Got it!"; }
	 
}
