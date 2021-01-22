package codabook.agecalculator.osgi.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import codabook.agecalculator.osgi.ifce.IAgeCalculator;
import codabook.agecalculator.osgi.ifce.AvailablePlace;

/*************************************************************************************************
 *
 * COMPANION CODE FOR THE BOOK “Component Oriented Development & Assembly – CODA
 * Using Java”
 * 
 * @author – Piram Manickam, Sangeetha S, Subrahmanya S V
 * @see - http://www.codabook.com
 * 
 *      <br>
 *      <br>
 *      <b>CODE CONTRIBUTORS</b> –
 *      <p>
 *      - Vishal Verma, Shikhar Johari, Soumya Bardhan, Rohit Jain, Karthika
 *      Nair, Vibhuti Pithwa, Vaasavi Lakshmi
 *      </p>
 * 
 **************************************************************************************************/

public class AgeCalculatorImpl implements IAgeCalculator {

	public ArrayList<AvailablePlace> shopArrayList;
	private String destination;
	DecimalFormat df = new DecimalFormat("0.00");

	public AgeCalculatorImpl() {
		try {
			// Read shop list from txt file
			File shopListCsv = new File("shop-list.txt");
			Scanner myReader = new Scanner(shopListCsv);

			// Add csv line to array list
			while (myReader.hasNextLine()) {
				String x = myReader.nextLine();
				List<String> data = new ArrayList<String>();
				data = Arrays.asList(x.split(","));
				this.shopArrayList.add(new AvailablePlace(data.get(0), data.get(1), Double.parseDouble(data.get(2)),
						Double.parseDouble(data.get(3))));
			}
			myReader.close();
			System.out.println("halo");
			System.out.println(shopArrayList);
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}

	private double getShopDistance(double userLatitude, double userLongitude, double shopLatitude, double shopLongitude) {
		double theta = userLongitude - shopLongitude;
		double distance = Math.sin(deg2rad(userLatitude)) * Math.sin(deg2rad(shopLatitude))
				+ Math.cos(deg2rad(userLatitude)) * Math.cos(deg2rad(shopLatitude)) * Math.cos(deg2rad(theta));

		distance = Math.acos(distance);
		distance = rad2deg(distance);
		distance = distance * 60 * 1.1515 * 1.609344;

		return distance;
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

	public int suggestPlace(int placeCode, double longitude, double latitude) {
		
		System.out.println("masuk dah");

		// Set destination based on code
		switch (placeCode) {
		case 1:
			this.destination = "barber shop";
			break;
		case 2:
			this.destination = "pet shop";
			break;
		case 3:
			this.destination = "laundry";
			break;
		case 4:
			this.destination = "restaurant";
			break;
		case 5:
			this.destination = "workshop";
			break;
		default:
			this.destination = "";
		}

		ArrayList<Double> allShopDistance = new ArrayList<Double>();
//		double max = Integer.MAX_VALUE;
//		String nearest = "";
		for (AvailablePlace shop : shopArrayList) {
			if (shop.type.equalsIgnoreCase(this.destination)) {
				double shopDistance = this.getShopDistance(latitude, longitude, shop.latitude, shop.longitude);
				allShopDistance.add(shopDistance);

				System.out.println("The distance from your current location to " + shop.name + " is "
						+ this.df.format(shopDistance) + " km");

//                for (int j =0; j<distances.size(); j++) {
//                    if (distances.get(j)<max) {
//                        max=distances.get(j);
//                        nearest = i.name;
//                    }
//                }
			}
		}
//		System.out.println("Nearest is " + nearest);

		return 1;
	}

}
