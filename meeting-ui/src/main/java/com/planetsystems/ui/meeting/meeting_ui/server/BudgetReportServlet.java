/**
 * 
 */
package com.planetsystems.ui.meeting.meeting_ui.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import com.planetsystems.core.utils.utils_core.services.FileAttachmentService;
import com.planetsystems.core.utils.utils_core.services.ProcuringEntityService;
import com.planetsystems.core.utils.utils_core.utils.ApplicationContextProvider;
import com.planetsystems.model.utils.utils_model.AttachmentType;
import com.planetsystems.model.utils.utils_model.FileAttachment;
import com.planetsystems.model.utils.utils_model.ProcuringEntity;
import com.planetsystems.ui.meeting.meeting_ui.client.util.ReportParameter;

@SuppressWarnings("serial")
public class BudgetReportServlet extends HttpServlet {

	// private
	// as per ReportsRecord
	private static final String REPORT_FILENAME = "reportFilename";
	private static final String DEFAULT_REPORTS_SERVICE_PATH = "/reports/";

	Properties prop = new Properties();
	InputStream input = null;

	private String databaseDriver = null;
	private String databaseUserName = null;
	private String databasePassword = null;
	private String databaseUrl = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Command received");
		generateReport(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		generateReport(request, response);
	}

	private Connection getConnection() {

		Connection connection = null;

		try {

			Class.forName(databaseDriver);

			connection = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return connection;
	}

	private void getProperties() {

		try {

			String filename = "persistence-dbc.properties";
			// String filename = "PROCNET_SETTINGS.properties";
			input = BudgetReportServlet.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			// load a properties file from class path, inside static method

			prop.load(input);

			databaseDriver = prop.getProperty("hibernate.connection.driver_class");
			databaseUrl = prop.getProperty("hibernate.connection.url");
			databaseUserName = prop.getProperty("hibernate.connection.username");
			databasePassword = prop.getProperty("hibernate.connection.password");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * Reading the file in bytes helps in reading the images in the report but
	 * the font types with
	 */
	void generateReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String reportFilename = request.getParameter(REPORT_FILENAME);
		String resourceName = DEFAULT_REPORTS_SERVICE_PATH + reportFilename;
		ServletOutputStream servletOutputStream = response.getOutputStream();
		File reportFile = new File(getServletConfig().getServletContext().getRealPath(resourceName));
		String reportRequest = request.getParameter("request");

		if (reportRequest != null) {

			InputStream logoStream = getLogoPath();
			parameters.put("logo", logoStream);
			parameters.put("companyName", getComanyName());


			try {
				getProperties();
				JasperReport jasperReport = JasperCompileManager.compileReport(reportFile.getPath());
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, getConnection());

				JRExporter exporter = null;
				// response.setContentType("application/rtf");
				response.setContentType("application/pdf");
				// response.setHeader("Content-Disposition",
				// "inline; filename=\"form16.rtf\"");
				// exporter = new JRRtfExporter();
				exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
				exporter.exportReport();
				servletOutputStream.flush();
				servletOutputStream.close();

			} catch (Exception e) {
				// display stack trace in the browser
				StringWriter stringWriter = new StringWriter();
				PrintWriter printWriter = new PrintWriter(stringWriter);
				e.printStackTrace(printWriter);
				response.setContentType("text/plain");
				response.getOutputStream().print(stringWriter.toString());
			}
		}
	}

	private ArrayList<String> extractReferenceNo(String message) {
		ArrayList<String> list = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(message, "/");
		while (tokenizer.hasMoreTokens()) {
			list.add(tokenizer.nextToken());
		}
		return list;

	}

	private InputStream getLogoPath() {
		InputStream inStream = null;
		try {

			ProcuringEntityService procuringEntityService = ApplicationContextProvider.getBean(ProcuringEntityService.class);
			ProcuringEntity procuringEntity = procuringEntityService.getActiveProcuringEntity();

			FileAttachmentService fileAttachmentService = ApplicationContextProvider.getBean(FileAttachmentService.class);

			FileAttachment attachment = fileAttachmentService.getFileAttachmentByAttachmentIdType(procuringEntity.getId(), AttachmentType.LOGO);

			saveFileToDisk(attachment);

			File downloadFile = new File(attachment.getId());
			inStream = new FileInputStream(downloadFile);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return inStream;
	}

	private String getComanyName() {
		ProcuringEntityService procuringEntityService = ApplicationContextProvider.getBean(ProcuringEntityService.class);
		ProcuringEntity procuringEntity = procuringEntityService.getActiveProcuringEntity();
		return procuringEntity.getEntityName();
	}

	public void saveFileToDisk(FileAttachment fileAttachment) {
		byte[] bFile = fileAttachment.getContent();

		try {

			FileOutputStream fileOuputStream = new FileOutputStream(fileAttachment.getId());
			fileOuputStream.write(bFile);
			fileOuputStream.close();

			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
