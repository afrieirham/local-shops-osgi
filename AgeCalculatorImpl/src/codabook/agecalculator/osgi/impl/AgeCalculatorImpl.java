package codabook.agecalculator.osgi.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;

import codabook.agecalculator.osgi.ifce.IAgeCalculator;

/*************************************************************************************************
*
* COMPANION CODE FOR THE BOOK “Component Oriented Development & Assembly – CODA Using Java”
* 
* @author – Piram Manickam, Sangeetha S, Subrahmanya S V
* @see -  http://www.codabook.com
* 
* <br><br><b>CODE CONTRIBUTORS</b> – <p>- Vishal Verma, Shikhar Johari, Soumya Bardhan, Rohit Jain,
* Karthika Nair, Vibhuti Pithwa, Vaasavi Lakshmi</p>

**************************************************************************************************/

public class AgeCalculatorImpl implements IAgeCalculator {

	public int calculateAge(Calendar dateOfBirth) {
		Calendar rightNow = new GregorianCalendar();

		int currentYear = rightNow.get(Calendar.YEAR);
		int currentMonth = rightNow.get(Calendar.MONTH);
		int currentDate = rightNow.get(Calendar.DATE);

		int birthYear = dateOfBirth.get(Calendar.YEAR);
		int birthMonth = dateOfBirth.get(Calendar.MONTH);
		int birthDate = dateOfBirth.get(Calendar.DATE);

		int age = 0;

		boolean isCurrentYearBdayPassed = (currentMonth > birthMonth)
				|| ((currentMonth == birthMonth) && (currentDate >= birthDate));

		if (isCurrentYearBdayPassed) {
			age = currentYear - birthYear;
		} else {
			age = currentYear - 1 - birthYear;
		}

		return age;
	}

}
