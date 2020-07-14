package com.planetsystems.core.meeting.services;

import java.util.List;

import com.planetsystems.model.meetings.AgendaItems;
import com.planetsystems.model.meetings.Meetings;
import com.planetsystems.model.meetings.Minutes;
import com.planetsystems.model.utils.utils_model.FinancialYear;

public interface MeetingsService {
	public boolean save(Meetings meeting);

	public boolean edit(Meetings meeting);

	public boolean delete(Meetings meeting);

	public boolean delete(String id);

	public Meetings find(String id);

	public List<Meetings> findAll();

	public List<Meetings> getMeetingsByFinancialYear(FinancialYear financialYear);
	
	public boolean save(Minutes minutes);

	public boolean edit(Minutes minutes);

	public boolean delete(Minutes minutes);

	public boolean deleteMinutes(String id);

	public Meetings findMinutes(String id);

	public List<Minutes> findAllMinutes();
	
	public List<Minutes> findMinutesByAgendaItem(AgendaItems agendaItem);
	public List<Minutes> findMinutesByMeeting(Meetings meeting);
}
