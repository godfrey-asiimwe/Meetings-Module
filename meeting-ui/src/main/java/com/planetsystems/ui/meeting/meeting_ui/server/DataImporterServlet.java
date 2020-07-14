package com.planetsystems.ui.meeting.meeting_ui.server;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.planetsystems.core.meeting.services.MeetingsService;
import com.planetsystems.core.meeting.util.ApplicationContextProvider;
import com.planetsystems.model.meetings.Meeting;
import com.planetsystems.model.utils.utils_model.Account;
import com.planetsystems.model.utils.utils_model.Activity;
import com.planetsystems.model.utils.utils_model.FinancialYear;
import com.planetsystems.model.utils.utils_model.FundingAgency;
import com.planetsystems.model.utils.utils_model.Item;
import com.planetsystems.model.utils.utils_model.LineItem;
import com.planetsystems.model.utils.utils_model.ProcuringEntity;
import com.planetsystems.ui.utils.utils_ui.client.utils.UtilsManager;


public class DataImporterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MeetingsService meetingsService = ApplicationContextProvider.getBean(MeetingsService.class);

	//private StudentClassRegisterService studentClassRegisterService = ApplicationContextProvider.getBean(StudentClassRegisterService.class);

	// User logedinUser = null;

	public DataImporterServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text");
		String status = "FAILED";
		String importrequest = request.getParameter("request");

		if (ServletFileUpload.isMultipartContent(request)) {

			FileItemFactory factory = new DiskFileItemFactory();

			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem item : items) {
					if (importrequest.equalsIgnoreCase("ImportBudget")) {

						String financialYearId = request.getParameter("financialYearId");
						

						System.out.println("financial Year"+financialYearId);
						

					/*	String term = request.getParameter("term");

						String classId = request.getParameter("classId");*/

						status = processBudgetRegisterExcelFile(item,financialYearId);

					} else {
						System.out.println("Request not applicable ");
					}

				}
			} catch (Exception ex) {
				ex.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occured while creating the file" + ex.getMessage());
			}
		} else {
			response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Request Content Type is not supported by servlet");
		}
		response.getWriter().print(status);
		response.flushBuffer();
		System.out.println("SUCCESS..");

	}

	private String processBudgetRegisterExcelFile(FileItem fileItem, String financialYearId) {
		System.out.println("Processing Meeting ExcelFile For Import:");
		String response = "";
		try {
			// logedinUser = userService.getLoggedInUser();

			XSSFWorkbook myWorkBook = new XSSFWorkbook(fileItem.getInputStream());
			for (int sheetNo = 0; sheetNo < myWorkBook.getNumberOfSheets(); sheetNo++) {
				XSSFSheet mySheet = myWorkBook.getSheetAt(sheetNo);
				/** We now need something to iterate through the cells. **/
				Iterator<Row> rowIter = mySheet.rowIterator();

				XSSFRow headerRow = mySheet.getRow(0);

				List<Meeting> budgets = new ArrayList<Meeting>();

				while (rowIter.hasNext()) {

					XSSFRow row = (XSSFRow) rowIter.next();
					Iterator<Cell> cellIter = row.cellIterator();

					String vote = null;
					String programme = null;
					String subprogramme = null;
					String lineItem = null;
					String fundingAgency = null;
					Float totalAmount = null;
					String comment=null;

					int cellCounter = 0;
					while (cellIter.hasNext()) {
						Cell cell = cellIter.next();
						System.out.println("cellCounter= " + cellCounter);
						if ((headerRow.getCell(cellCounter)) != null) {
							if ((headerRow.getCell(cellCounter).getStringCellValue()).equalsIgnoreCase("Programme")) {
								cell.setCellType(1);
								programme = (cell.getStringCellValue());
								System.out.println("" + cell.getRowIndex());
							} else if ((headerRow.getCell(cellCounter).getStringCellValue()).equalsIgnoreCase("Subprogramme")) {
								cell.setCellType(1);
								subprogramme = (cell.getStringCellValue());
								System.out.println("" + cell.getRowIndex());
							} else if ((headerRow.getCell(cellCounter).getStringCellValue()).equalsIgnoreCase("LineItem")) {
								cell.setCellType(1);
								lineItem = (cell.getStringCellValue());
								System.out.println("" + cell.getRowIndex());
							} else if ((headerRow.getCell(cellCounter).getStringCellValue()).equalsIgnoreCase("fundingAgency")) {
								cell.setCellType(1);
								fundingAgency = (cell.getStringCellValue());
								System.out.println("" + cell.getRowIndex());
							} else if ((headerRow.getCell(cellCounter).getStringCellValue()).equalsIgnoreCase("Vote")) {
								cell.setCellType(1);
								vote = (cell.getStringCellValue());
								System.out.println("" + cell.getRowIndex());
							} else if ((headerRow.getCell(cellCounter).getStringCellValue()).equalsIgnoreCase("comment")) {
								cell.setCellType(1);
								comment = (cell.getStringCellValue());
								System.out.println("" + cell.getRowIndex());
							}else if ((headerRow.getCell(cellCounter).getStringCellValue()).equalsIgnoreCase("totalAmount")) {
								cell.setCellType(1);
								if(UtilsManager.getInstance().isFloat(cell.getStringCellValue())){
									totalAmount =Float.parseFloat(cell.getStringCellValue());
								} 
								System.out.println("" + cell.getRowIndex());
							}else {
								System.out.println("no match== counter==" + cellCounter);
							}

						} else {
							System.out.println("it is null");
						}
						cellCounter++;
					}
					if (row.getRowNum() == 0) {

					} else {
						
						budgets.add(getBudgetToImport(financialYearId,programme, subprogramme, lineItem, fundingAgency, vote, comment,totalAmount));
						
						System.out.println("Meeting Size"+budgets.size());

					}

				}

				//boolean saved = meetingsService.importBudget(budgets);
				/*
				 * if (saved == true) { response = "Import successful"; } else { response =
				 * "Import Not successfully"; System.out.println("Import Not successfully...>");
				 * }
				 */

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public Meeting getBudgetToImport(String financialYearId,String programme, String subprogramme, String lineItem, String fundingAgency, String vote, String comment,Float totalAmount) throws ParseException {

		FinancialYear finanncialYear=new FinancialYear();
		
		finanncialYear.setId(financialYearId);
		
		System.out.println("financial Year"+financialYearId);
		
		ProcuringEntity procuringEntity=new ProcuringEntity();
		procuringEntity.setEntityName(vote);
		
		Account account=new Account();
		account.setAccountCode(programme);
		
		Activity activity=new Activity();
		activity.setActivityCode(subprogramme);
		
		LineItem lineItemto=new LineItem();
		lineItemto.setCode(lineItem);
		
		FundingAgency fundingagency=new FundingAgency();
		fundingagency.setCode(fundingAgency);
		
		Date date=new Date();
		
		Meeting budgetTo=new Meeting();
		budgetTo.setFinancialYear(finanncialYear);
		budgetTo.setProcuringEntity(procuringEntity);
		budgetTo.setAccount(account);
		budgetTo.setActivity(activity);
		budgetTo.setLineItem(lineItemto);
		budgetTo.setFundingAgency(fundingagency);
		budgetTo.setComment(comment);
		budgetTo.setTotalAmount(totalAmount);
		budgetTo.setDateMade(date);
		
		return budgetTo;
		
	}


}
