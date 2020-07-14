package com.planetsystems.core.meeting.daos.impl;

import org.springframework.stereotype.Repository;

import com.planetsystems.core.meeting.daos.MeetingDao;
import com.planetsystems.model.meetings.Meetings;

@Repository("MeetingDao")
public class MeetingDaoImpl extends MeetingBaseDaoImpl<Meetings> implements MeetingDao{

}
