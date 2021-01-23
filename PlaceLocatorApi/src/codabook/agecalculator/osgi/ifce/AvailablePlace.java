package codabook.agecalculator.osgi.ifce;

public class AvailablePlace {
	public String type;
	public String name;
    public double longitude;
    public double latitude;
    public double distanceToUser;
    
    public AvailablePlace(String type, String name, double longitude, double latitude) {
    	this.type = type;
    	this.name = name;
    	this.longitude = longitude;
    	this.latitude = latitude;
    }
}
