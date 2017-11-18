package com.intellectDesign.springBoot.crudoperations.com.intellectDesign.springBoot.crudoperations.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Utility class
 * @author admin
 *
 */
public class UtilOperations {

	private static final DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	
	/**
	 * Checks if the date is valid
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static boolean isDateValid(String date) throws ParseException{
	   Date dt = sdf.parse(date);
	   return dt.after(Calendar.getInstance().getTime());
	}
}


