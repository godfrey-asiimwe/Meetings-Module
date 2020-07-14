package com.planetsystems.ui.meeting.meeting_ui.client.util;

import com.google.gwt.http.client.URL;
import com.planetsystems.ui.utils.utils_ui.client.widgets.ReportDisplayWindow;

public class ReportGenetor {
	
	public static final String DEFAULT_REPORTS_SERVICE_PATH = "budgetReport";
	private static String REPORT_FILENAME = "reportFilename";
	private static String reportFilename = null;

	public ReportGenetor() {

	}

	public static void generateQuotationReport(String quotationId) {

		reportFilename = "quotationReport.jrxml";
		StringBuilder url = new StringBuilder();
		url.append(DEFAULT_REPORTS_SERVICE_PATH).append("?");// .append(HOST_FILENAME)
		String arg0Name = URL.encodeQueryString(REPORT_FILENAME);
		url.append(arg0Name);
		url.append("=");
		String arg0Value = URL.encodeQueryString(reportFilename);
		url.append(arg0Value).append("&request=" + ReportParameter.PRICE_QUOTATION_REPORT+ "&" + ReportParameter.quotationId_PARAMETER+ "=" + quotationId);
		ReportDisplayWindow window = new ReportDisplayWindow(url.toString(), "Quotation Report Preview");
		window.show();

	}

}
