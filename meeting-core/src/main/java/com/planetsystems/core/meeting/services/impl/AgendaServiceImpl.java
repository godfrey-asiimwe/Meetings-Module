package com.planetsystems.core.meeting.services.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.core.meeting.daos.AgendaDAO;
import com.planetsystems.core.meeting.services.AgendaService;
import com.planetsystems.model.meetings.Agenda;
import com.planetsystems.model.meetings.Meetings;

@Service("AgendaService")
@Transactional
public class AgendaServiceImpl implements AgendaService {
	

	@Autowired
	private AgendaDAO agendaDAO;
	

	public boolean save(Agenda agenda) {
		try {
			agenda.setAgendaNo(generateRefNo(agenda));
			agendaDAO.save(agenda);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public boolean edit(Agenda agenda) {
		try { 
			agendaDAO.update(agenda);
			return true;
		}catch(Exception ex){
			
			System.out.println("EDIT ERROR"+ex.getMessage());
			
		}
		return false;
	}

	public boolean delete(Agenda agenda) {
		try {
			agendaDAO.remove(agenda);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public Agenda find(String id) {
		try {
			return agendaDAO.find(id);
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

	public List<Agenda> findAll() {
		// TODO Auto-generated method stub
		try {
			return agendaDAO.find();
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}
	
	
	private String generateRefNo(Agenda agenda) {
		String reqId=null;
		try {

			Search search = new Search();
			int counter = agendaDAO.count(search);
			
			reqId ="AGENDA" + "/"
					+ (String.format("%05d", ++counter));
			
			return reqId;

			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return reqId;
	}

	
}
