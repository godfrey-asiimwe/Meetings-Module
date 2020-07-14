package com.planetsystems.core.meeting.daos.impl;

import org.springframework.stereotype.Repository;

import com.planetsystems.core.meeting.daos.AgendaItemsDAO;
import com.planetsystems.model.meetings.AgendaItems;


@Repository("AgendaItemsDAO")
public class AgendaItemsDAOImpl extends MeetingBaseDaoImpl<AgendaItems> implements
		AgendaItemsDAO {

}
