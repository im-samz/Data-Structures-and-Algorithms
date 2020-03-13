/**
 * Location Object stores the coordinates of a pixel. 
 * It will be used as the key in the binary search tree.
 * @author Sam
 *
 */
public class Location {
	
	/**
	 * Instance variables.
	 */
	private int x;
	private int y;
	
	/**
	 * Constructor method initializes x and y with the passed values.
	 * @param x
	 * @param y
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter method returns the x coordinate.
	 * @return
	 */
	public int xCoord() {
		return x;
	}
	
	/**
	 * Getter method returns the y coordinate.
	 * @return
	 */
	public int yCoord() {
		return y;
	}
	
	/**
	 * Method compares the location with another.
	 * The x coordinate is given precedence, if both x's are equal, then the y coordinates are compared.
	 * @param p
	 * @return 1 if this location is larger the the other; -1 if this location is smaller than the other; 0 if both are the same.
	 */
	public int compareTo(Location p) {
		if (this.x > p.xCoord())
			return 1;
		else if (this.x < p.xCoord())
			return -1;
		else {
			if (this.y > p.yCoord())
				return 1;
			else if (this.y < p.yCoord())
				return -1;
			else return 0;		// If both the x and y coordinate had no difference
		}
	}
}
