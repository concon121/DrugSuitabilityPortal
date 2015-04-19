package uk.co.cbray.msc.nhsdsp.utils;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class AgeHelperTest {
	
	@Test
	public void testCalculateAgeReturnsTheCorrectAge() {
		
		int expectedAge = 45;
		
		Calendar dob = new GregorianCalendar();
		
		Calendar today = new GregorianCalendar();
		int year = today.get(Calendar.YEAR);
		
		// Set date of birth to the first of JAN, 45 years ago.
		dob.set(year - expectedAge, Calendar.JANUARY, 1);
		
		int actualAge = AgeHelper.calculateAge(dob);
		
		assertEquals(expectedAge, actualAge);
	}
}
