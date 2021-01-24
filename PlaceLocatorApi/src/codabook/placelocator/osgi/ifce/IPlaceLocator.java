package codabook.placelocator.osgi.ifce;

public interface IPlaceLocator {
	public AvailablePlace suggestPlace(int placeCode, double longitude, double latitude);
	
}
