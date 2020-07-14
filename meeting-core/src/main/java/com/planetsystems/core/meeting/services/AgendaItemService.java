package com.planetsystems.core.meeting.services;

import java.util.List;

import com.planetsystems.model.meetings.Agenda;
import com.planetsystems.model.meetings.AgendaItems;


public interface AgendaItemService {
	
	public boolean save(AgendaItems agendaItems);

	public boolean edit(AgendaItems agendaItems);

	public boolean delete(AgendaItems agendaItems);

	public boolean delete(String id);

	public AgendaItems find(String id);

	public List<AgendaItems> findAll();
	public List<AgendaItems> findAgendaItemByAgenda(Agenda agenda);

}
