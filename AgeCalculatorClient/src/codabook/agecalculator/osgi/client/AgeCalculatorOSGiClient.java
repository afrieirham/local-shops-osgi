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

	@SuppressWarnings("unchecked")
	public void activate(ComponentContext context) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Choose where do you want to go?");
		System.out.println("1. Barber Shop");
		System.out.println("2. Pet Shop");
		System.out.println("3. Laundry");
		System.out.println("4. Restaurant");
		System.out.println("5. Workshop");
		int placeCode = scanner.nextInt();

		System.out.println("Please type in your longitude.");
		double longitude = scanner.nextDouble();

		System.out.println("Please type in your latitude");
		double latitude = scanner.nextDouble();
		

		if (reference != null) {
			ageCalculator = (IAgeCalculator) context.locateService("IAgeCalculator", reference);

			int age = ageCalculator.suggestPlace(placeCode, longitude, latitude);
			System.out.println("Masuk lu");

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
