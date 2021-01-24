package codabook.placelocator.osgi.client;

import java.text.DecimalFormat;
import java.util.Scanner;

import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;

import codabook.placelocator.osgi.ifce.AvailablePlace;
import codabook.placelocator.osgi.ifce.IPlaceLocator;

/*************************************************************************************************
*
* COMPANION CODE FOR THE BOOK �Component Oriented Development & Assembly � CODA Using Java�
* 
* @author � Piram Manickam, Sangeetha S, Subrahmanya S V
* @see -  http://www.codabook.com
* 
* <br><br><b>CODE CONTRIBUTORS</b> � <p>- Vishal Verma, Shikhar Johari, Soumya Bardhan, Rohit Jain,
* Karthika Nair, Vibhuti Pithwa, Vaasavi Lakshmi</p>

**************************************************************************************************/

public class PlaceLocatorOSGiClient {

	ComponentContext context;
	ServiceReference reference;
	IPlaceLocator placeLocator;
	DecimalFormat df = new DecimalFormat("0.00");

	public void activate(ComponentContext context) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Choose where do you want to go?");
		System.out.println("1. Barber Shop");
		System.out.println("2. Grocery");
		System.out.println("3. Laundry");
		System.out.println("4. Restaurant");
		System.out.println("5. Workshop");
		int placeCode = scanner.nextInt();

		System.out.println("Please type in your longitude.");
		double longitude = scanner.nextDouble();

		System.out.println("Please type in your latitude");
		double latitude = scanner.nextDouble();
		

		if (reference != null) {
			placeLocator = (IPlaceLocator) context.locateService("IPlaceLocator", reference);
			
			AvailablePlace shop = placeLocator.suggestPlace(placeCode, longitude, latitude);
			
			System.out.println("");
			System.out.println("=====================================================================");
			System.out.println("Place that is nearest to you is" + shop.name);
			System.out.println("It's about " + df.format(shop.distanceToUser) + "km from your current location.");
			System.out.println("=====================================================================");
			System.out.println("");

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
