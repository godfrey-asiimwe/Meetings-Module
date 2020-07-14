package com.planetsystems.core.meeting.daos.impl;

import org.springframework.stereotype.Repository;

import com.planetsystems.core.meeting.daos.CommitteeMemberDAO;
import com.planetsystems.model.meetings.CommitteeMember;


@Repository("CommitteeMemberDAO")
public class CommitteeMemberDAOImpl extends MeetingBaseDaoImpl<CommitteeMember> implements
CommitteeMemberDAO {

}
