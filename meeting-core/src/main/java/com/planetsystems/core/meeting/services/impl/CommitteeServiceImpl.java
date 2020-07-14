package com.planetsystems.core.meeting.services.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.core.meeting.daos.CommitteeDao;
import com.planetsystems.core.meeting.daos.CommitteeMemberDAO;
import com.planetsystems.core.meeting.services.CommitteeService;
import com.planetsystems.core.utils.utils_core.services.UserService;
import com.planetsystems.model.meetings.CommitteeMember;
import com.planetsystems.model.meetings.Committees;
import com.planetsystems.model.utils.utils_model.Status;
import com.planetsystems.model.utils.utils_model.User;

@Service("CommitteeService")
@Transactional
public class CommitteeServiceImpl implements CommitteeService {

	@Autowired
	private CommitteeDao committeeDao;
	
	@Autowired
	private CommitteeMemberDAO committeeMemberDAO;

	@Autowired
	private UserService userService;

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean save(Committees committee) {
	
		try {
			committeeDao.save(committee);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean edit(Committees committee) {
		try {
			committeeDao.update(committee);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean delete(Committees committee) {
		try {
			System.out.println("committee at service"+committee.getId());
			committeeDao.remove(committee);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public boolean delete(String id) {
		try {
			committeeDao.deleteById(id);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public Committees find(String id) {
		try {
			return committeeDao.find(id);
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

	public List<Committees> findAll() {
		try {
			return committeeDao.find();
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

	public List<Committees> getCommitteeByUser(User user) {
		List<Committees> list = new ArrayList<Committees>();
		try {
			Search search = new Search();
			search.addFilterEqual("user", user);
			search.addFilterEqual("status", Status.ACTIVE);
			List<Committees>committees = committeeDao.search(search);

			for (Committees committee : committees) {
				list.add(committee);
			}
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return list;
	}

	public boolean save(CommitteeMember committeeMember) {
		try {
			committeeMemberDAO.save(committeeMember);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public boolean edit(CommitteeMember committeeMember) {
		try {
			committeeMemberDAO.update(committeeMember);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public boolean delete(CommitteeMember committeeMember) {
		try {
			committeeMemberDAO.remove(committeeMember);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public CommitteeMember findCommitteMemberById(String id) {
		try {
			return committeeMemberDAO.find(id);
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

	public List<CommitteeMember> findMembersAll() {
		try {
			return committeeMemberDAO.find();
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

	public List<CommitteeMember> getCommitteeMemberByCommittee(Committees committees) {
		List<CommitteeMember> list = new ArrayList<CommitteeMember>();
		try {
			Search search = new Search();
			search.addFilterEqual("committee", committees);
			//search.addFilterEqual("status", Status.ACTIVE);
			List<CommitteeMember> committeeMembers = committeeMemberDAO.search(search);

			for (CommitteeMember committeeMember : committeeMembers) {
				list.add(committeeMember);
			}
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return list;
	}

}
