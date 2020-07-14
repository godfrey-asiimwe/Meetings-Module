package com.planetsystems.core.meeting.daos.impl;

import org.springframework.stereotype.Repository;

import com.planetsystems.core.meeting.daos.MinutesDao;
import com.planetsystems.model.meetings.Minutes;

@Repository("MinutesDao")
public class MinutesDaoImpl extends MeetingBaseDaoImpl<Minutes> implements MinutesDao{

}
