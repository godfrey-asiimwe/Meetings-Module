package com.planetsystems.ui.meeting.meeting_ui.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.lang.String;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.planetsystems.core.meeting.services.AgendaItemService;
import com.planetsystems.core.meeting.services.AgendaService;
import com.planetsystems.core.meeting.services.CommitteeService;
import com.planetsystems.core.meeting.services.MeetingsService;
import com.planetsystems.core.utils.utils_core.services.FinancialYearService;
import com.planetsystems.model.meetings.Agenda;
import com.planetsystems.model.meetings.Committees;
import com.planetsystems.model.meetings.Meetings;
import com.planetsystems.model.utils.utils_model.FinancialYear;



/**
 * Servlet implementation class MeetingsReportsServlet
 */
public class MeetingsReportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private MeetingsService meetingsService;
	
	@Autowired
	private FinancialYearService financialYearService;
	
	@Autowired
	private AgendaService agendaService;
	
	@Autowired
	private CommitteeService committeeService;
	
	@Autowired
	private AgendaItemService agendaItemService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MeetingsReportsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		List<Meetings> meetingsList = new LinkedList<Meetings>();
		List<Meetings> meetingsListDTO = new LinkedList<Meetings>();
		
		meetingsList = meetingsService.findAll();
		
		for(Meetings meeting:meetingsList) {
			Meetings meetingDTO=new Meetings();
			meetingDTO.setId(meeting.getId());
			meetingDTO.setTitle(meeting.getTitle());
			meetingDTO.setVenue(meeting.getVenue());
			meetingDTO.setProcurementRefNo(meeting.getProcurementRefNo());
			meetingDTO.setStatus(meeting.getStatus());
			meetingDTO.setDate(meeting.getDate());
			meetingDTO.setComment(meeting.getComment());
			
			Agenda agenda=new Agenda();
			agenda=agendaService.find(meeting.getAgenda().getId());
			
			meetingDTO.setAgenda(agenda);
			
			Committees committee=new Committees();
			committee=committeeService.find(meeting.getCommittee().getId());
			
			meetingDTO.setCommittee(committee);
			
			FinancialYear financialYear=new FinancialYear();
			financialYear=financialYearService.find(meeting.getFinancialYear().getId());
			
			
			meetingDTO.setFinancialYear(financialYear);
			
			meetingsListDTO.add(meetingDTO);
			
			try {
			    // create book object
			    
			    // convert book object to JSON
			    String json = new Gson().toJson(meetingsListDTO);

			    // print JSON string
			    System.out.println(json);

			} catch (Exception ex) {
			    ex.printStackTrace();
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void Meetings(List<Meetings> meeting) {
		try {
		    // create book object
		    

		    // convert book object to JSON
		    String json = new Gson().toJson(meeting);

		    // print JSON string
		    System.out.println(json);

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	private void createJson(String id, String folder) throws IOException {
		

		FileWriter file = new FileWriter(folder + "" + id + ".json");

		try {

/*			BiderInvitation biderInvitation = getBiderInvitation(id);

			if (biderInvitation != null) {

				Gson gson = new Gson();
				String string1=gson.
				
				String jsonString = gson.toJson(biderInvitation);

				System.out.println("jsonString: " + jsonString);

				file.write(jsonString);*/

			/* } */

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			file.flush();
			file.close();

		}

	}


}
