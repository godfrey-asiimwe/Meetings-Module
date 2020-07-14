package com.planetsystems.core.meeting.services;

import java.util.List;

import com.planetsystems.model.meetings.CommitteeMember;
import com.planetsystems.model.meetings.Committees;
import com.planetsystems.model.utils.utils_model.User;


public interface CommitteeService {
	
	public boolean save(Committees committee);

	public boolean edit(Committees committeet);

	public boolean delete(Committees committee);

	public boolean delete(String id);

	public Committees find(String id);

	public List<Committees> findAll();

	public List<Committees> getCommitteeByUser(User user);
	
	public boolean save(CommitteeMember committeeMember);
	public boolean edit(CommitteeMember committeeMember);
	public boolean delete(CommitteeMember committeeMember);

	public CommitteeMember findCommitteMemberById(String id);

	public List<CommitteeMember> findMembersAll();

	public List<CommitteeMember> getCommitteeMemberByCommittee(Committees committees);

}
