package uk.co.cbray.msc.nhsdsp.utils;

import java.util.Calendar;

import org.joda.time.LocalDate;
import org.joda.time.Years;

/**
 * Helper class for all Age realted functions.
 * 
 * @author Connor Bray
 */
public class AgeHelper {

	/**
	 * Uses the Joda Time api to calculate a persons age, given their date of
	 * birth.
	 * 
	 * @param dob
	 *            The date of birth.
	 * @return The calculated age.
	 */
	public static int calculateAge(Calendar dob) {

		int year = dob.get(Calendar.YEAR);
		int month = dob.get(Calendar.MONTH) + 1;
		int date = dob.get(Calendar.DATE);

		LocalDate birthdate = new LocalDate(year, month, date);
		LocalDate now = new LocalDate();
		Years age = Years.yearsBetween(birthdate, now);

		return age.getYears();
	}

}
