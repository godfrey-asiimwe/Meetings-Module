package com.planetsystems.core.meeting.daos.impl;

import org.springframework.stereotype.Repository;

import com.planetsystems.core.meeting.daos.AgendaDAO;
import com.planetsystems.model.meetings.Agenda;


@Repository("AgendaDAO")
public class AgendaDAOImpl extends MeetingBaseDaoImpl<Agenda> implements
		AgendaDAO {

}
