package com.planetsystems.ui.meeting.meeting_ui.server.service;

import java.util.ArrayList;
import java.util.List;

import com.planetsystems.ui.meeting.meeting_ui.server.Message;


public class MessageService {

	public List<Message> getAllMessages() {
		// TODO Auto-generated constructor stub
		
		Message s1=new Message(1L,"Hello Wold","godfrey");
		Message s2=new Message(2L,"Hello sir","godfrey");
		
		List<Message> list=new ArrayList<Message>();
		list.add(s1);
		list.add(s2);
		
		return list;
	}
	
	
	
	

}
