package com.planetsystems.ui.meeting.meeting_ui.client.util;

import java.util.List;
import com.google.gwt.i18n.client.NumberFormat;

public class BudgetManager {
	private static BudgetManager instance = new BudgetManager();

	private BudgetManager() { 

	}

	public static BudgetManager getInstance() { 
		return instance;
	}
	
	public String formatCash(int cash) {
        String formatedCash = null;
        NumberFormat numberFormat = NumberFormat.getFormat("0,000");
        formatedCash = numberFormat.format(cash);
        return formatedCash;
    }
	
	public String formatCash(Double cash) {
        String formatedCash = null;
        NumberFormat numberFormat = NumberFormat.getFormat("0,000");
        formatedCash = numberFormat.format(cash);
        return formatedCash;
    }
	
	public String formatCash(long cash) {
        String formatedCash = null;
        NumberFormat numberFormat = NumberFormat.getFormat("0,000");
        formatedCash = numberFormat.format(cash);
        return formatedCash;
    }

    public String unformatCash(String cash) {
        String unformatedCash = null;
        try {
            unformatedCash = cash.replaceAll(",", "");
        } catch (Exception e) {
        }
        return unformatedCash;
    }

	public boolean isValidInput(String input) {
		if (input != null) {
			if (!(input.replace(" ", "")).isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public boolean isValidInputs(List<String> inputs) {
		boolean output = false;
		if (inputs != null) {
			for (String input : inputs) {
				if (input != null) {
					if (!(input.replace(" ", "")).isEmpty()) {
						output = true;
					} else {
						return false;
					}
				} else {
					return false;
				}

			}

		}
		return output;
	}

	public boolean isLong(String number) {
		try {
			Long.parseLong(number);
			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

}
