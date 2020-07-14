package com.planetsystems.core.meeting.daos.impl;

import org.springframework.stereotype.Repository;

import com.planetsystems.core.meeting.daos.CommitteeDao;
import com.planetsystems.model.meetings.Committees;

@Repository("CommitteeDao")
public class CommitteeDaoImpl extends CommitteeBaseDaoImpl<Committees> implements CommitteeDao{

}
