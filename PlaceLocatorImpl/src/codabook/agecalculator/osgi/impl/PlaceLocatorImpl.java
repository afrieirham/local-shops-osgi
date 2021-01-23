package codabook.agecalculator.osgi.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import codabook.agecalculator.osgi.ifce.IPlaceLocator;
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

public class PlaceLocatorImpl implements IPlaceLocator {

	public ArrayList<AvailablePlace> shopArrayList = new ArrayList<AvailablePlace>();
	private String destination;
	DecimalFormat df = new DecimalFormat("0.00");

	public PlaceLocatorImpl() {
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

	public AvailablePlace suggestPlace(int placeCode, double longitude, double latitude) {

		// Set destination based on code
		switch (placeCode) {
			case 1:
				this.destination = "barber";
				break;
			case 2:
				this.destination = "groceries";
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
		
		System.out.println("");
		System.out.println("=====================================================================");
		System.out.println("Here are a few place(s) that is relevant to your destination.");
		System.out.println("");
		
		// Initialize relevant shop distance
		ArrayList<AvailablePlace> relevantShop = new ArrayList<AvailablePlace>();
		for (AvailablePlace shop : this.shopArrayList) {
			
			// If shop type is same with user destination
			if (shop.type.equalsIgnoreCase(this.destination)) {
				
				// Show relevant location distance
				double shopDistance = this.getShopDistance(latitude, longitude, shop.latitude, shop.longitude);
				System.out.println("The distance from your current location to" + shop.name + " is " + this.df.format(shopDistance) + " km");
				
				// Add shop distance to distanceToUser
				shop.distanceToUser = shopDistance;
				
				// Add shop to relevant list
				relevantShop.add(shop);
			}
			
		}
		
		System.out.println("=====================================================================");
		System.out.println("");
		
		// Find nearest shop
		double minDistance = Integer.MAX_VALUE;
		AvailablePlace nearestShop = null;
		
		for (AvailablePlace shop : relevantShop) {
			// Get nearest shop
			if(shop.distanceToUser < minDistance) {
				minDistance = shop.distanceToUser;
				nearestShop = shop;
			}
		}

		return nearestShop;
	}

}
