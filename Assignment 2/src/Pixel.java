/**
 * Pixel class contains its location and its colour.
 * @author Sam
 *
 */
public class Pixel {
	
	/**
	 * Instance variables.
	 */
	private Location location;
	private int colour;
	
	/**
	 * Constructor initializes the instance variables.
	 * @param p
	 * @param colour
	 */
	public Pixel(Location p, int colour) {
		this.location = p;
		this.colour = colour;
	}
	
	/**
	 * Getter method returns the pixel's location.
	 * @return
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * Getter method returns the pixel's colour.
	 * @return
	 */
	public int getColor() {
		return colour;
	}
}
