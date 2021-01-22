package codabook.agecalculator.osgi.client;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;

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

public class AgeCalculatorOSGiClient {

	ComponentContext context;
	ServiceReference reference;
	IAgeCalculator ageCalculator;

	public void activate(ComponentContext context) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("What is your year of birth?");
		int year = scanner.nextInt();

		System.out.println("What is your month of birth (1-12)?");
		int month = scanner.nextInt();

		System.out.println("What is your date of birth (1-31)?");
		int date = scanner.nextInt();

		Calendar dateOfBirth = new GregorianCalendar();
		dateOfBirth.clear();
		dateOfBirth.set(year, month - 1, date);

		if (reference != null) {
			ageCalculator = (IAgeCalculator) context.locateService(
					"IAgeCalculator", reference);

			int age = ageCalculator.calculateAge(dateOfBirth);

			System.out.println("Your age is " + age);

		}

	}

	public void gotService(ServiceReference reference) {
		System.out.println("Bind Service");
		this.reference = reference;
	}

	public void lostService(ServiceReference reference) {
		System.out.println("unbind Service");
		this.reference = null;
	}

}
