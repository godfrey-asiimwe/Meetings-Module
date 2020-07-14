package com.planetsystems.core.meeting.services.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.core.meeting.daos.AgendaItemsDAO;
import com.planetsystems.core.meeting.services.AgendaItemService;
import com.planetsystems.model.meetings.Agenda;
import com.planetsystems.model.meetings.AgendaItems;
import com.planetsystems.model.meetings.Meeting;
import com.planetsystems.model.utils.utils_model.Status;

@Service("AgendaItemService")
@Transactional
public class AgendaItemServiceImpl implements AgendaItemService {
	
	@Autowired
	private AgendaItemsDAO agendaItemsDAO;

	public boolean save(AgendaItems agendaItems) {
		try {
			agendaItemsDAO.save(agendaItems);
			return true;
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public boolean edit(AgendaItems agendaItems) {
		try {
			
			agendaItemsDAO.update(agendaItems);
			
			return true;
			
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}

	public boolean delete(AgendaItems agendaItems) {
		try {
			
			agendaItemsDAO.remove(agendaItems);
			
			return true;
			
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		
		return false;
	}

	public boolean delete(String id) {
		return false;
	}

	public AgendaItems find(String id) {
		try {
			return agendaItemsDAO.find(id);
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

	public List<AgendaItems> findAll() {
		try {
			return agendaItemsDAO.find();
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

	public List<AgendaItems> findAgendaItemByAgenda(Agenda agenda) {
		
		List<AgendaItems> list = new ArrayList<AgendaItems>();
		
		try {
			Search search = new Search();
			search.addFilterEqual("agenda",agenda);
			List<AgendaItems> agendaItems = agendaItemsDAO.search(search);

			for (AgendaItems agendaItem : agendaItems) {
				list.add(agendaItem);
			}
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		
		return list;
	}

	
}
