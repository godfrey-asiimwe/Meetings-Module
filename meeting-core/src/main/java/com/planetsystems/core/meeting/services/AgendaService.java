package com.planetsystems.core.meeting.services;

import java.util.List;

import com.planetsystems.model.meetings.Agenda;


public interface AgendaService {
	
	public boolean save(Agenda agenda);

	public boolean edit(Agenda agenda);

	public boolean delete(Agenda agenda);

	public boolean delete(String id);

	public Agenda find(String id);

	public List<Agenda> findAll();

}
