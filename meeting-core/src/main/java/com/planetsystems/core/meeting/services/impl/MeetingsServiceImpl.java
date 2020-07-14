package com.planetsystems.core.meeting.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.core.meeting.daos.MeetingDao;
import com.planetsystems.core.meeting.daos.MinutesDao;
import com.planetsystems.core.meeting.services.MeetingsService;
import com.planetsystems.core.utils.utils_core.services.FinancialYearService;
import com.planetsystems.core.utils.utils_core.services.ProcuringEntityService;
import com.planetsystems.model.meetings.AgendaItems;
import com.planetsystems.model.meetings.Meetings;
import com.planetsystems.model.meetings.Minutes;
import com.planetsystems.model.utils.utils_model.FinancialYear;
import com.planetsystems.model.utils.utils_model.Status;

@Service("MeetingsService")
@Transactional
public class MeetingsServiceImpl implements MeetingsService {

	@Autowired
	private MeetingDao meetingsDao;
	
	@Autowired
	private MinutesDao minutesDao;

	@Autowired
	private FinancialYearService financialYearService;

	@Autowired
	private ProcuringEntityService procuringEntityService;

	public boolean save(Meetings meeting) {
		try {
			meeting.setProcurementRefNo(generateRefNo(meeting));
			meetingsDao.save(meeting);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public boolean edit(Meetings meeting) {
		try {
			
			meetingsDao.update(meeting);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public boolean delete(Meetings meeting) {
		try {
			meetingsDao.remove(meeting);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public boolean delete(String id) {
		try {
			meetingsDao.deleteById(id);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public Meetings find(String id) {
		try {
			Search search = new Search();
			search.addFilterEqual("id", id);
			return meetingsDao.searchUnique(search);
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

	public List<Meetings> findAll() {
		try {
			return meetingsDao.find();
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

	public List<Meetings> getMeetingsByFinancialYear(FinancialYear financialYear) {
		List<Meetings> list = new ArrayList<Meetings>();
		try {
			Search search = new Search();
			search.addFilterEqual("financialYear", financialYear);
			search.addFilterEqual("status", Status.ACTIVE);
			List<Meetings> budgets = meetingsDao.search(search);

			for (Meetings budget : budgets) {
				list.add(budget);
			}
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return list;
	}

	private String generateRefNo(Meetings meeting) {
		String refNo = null;
	
		try {

			Search search = new Search();
			int counter = meetingsDao.count(search);
			
			FinancialYear financialYear = financialYearService.find(meeting.getFinancialYear().getId());
			String year = financialYear.getName().replace("/", "-");
			
			refNo ="MEETING" + "/" + year+"/"
					+ (String.format("%05d", ++counter));
			
			return refNo;

			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return refNo;
	}

	private int getMeetingsCount() {
		int count = 0;
		for (Meetings meeting : findAll()) {
			count++;
		}
		return count;
	}

	public boolean save(Minutes minutes) {
		try {
			minutesDao.save(minutes);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public boolean edit(Minutes minutes) {
	try {
			
		minutesDao.update(minutes);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public boolean delete(Minutes minutes) {
		try {
			minutesDao.remove(minutes);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public boolean deleteMinutes(String id) {
		try {
			minutesDao.deleteById(id);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public Meetings findMinutes(String id) {
		try {
			Search search = new Search();
			search.addFilterEqual("id", id);
			return minutesDao.searchUnique(search);
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

	public List<Minutes> findAllMinutes() {
		try {
			return minutesDao.find();
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<Minutes> findMinutesByAgendaItem(AgendaItems agendaItem) {
		List<Minutes> list = new ArrayList<Minutes>();
		try {
			Search search = new Search();
			search.addFilterEqual("agendaItem", agendaItem);
			List<Minutes> minutes = minutesDao.search(search);

			for (Minutes minute : minutes) {
				list.add(minute);
			}
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return list;
	}

	@Transactional(readOnly = true)
	public List<Minutes> findMinutesByMeeting(Meetings meeting) {
		List<Minutes> list = new ArrayList<Minutes>();
		try {
			Search search = new Search();
			search.addFilterEqual("meeting", meeting);
			List<Minutes> minutes = minutesDao.search(search);

			for (Minutes minute : minutes) {
				list.add(minute);
			}
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return list;
	}

}
