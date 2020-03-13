/**
 * GraphicalFigure class contains information of a graphical figure in Pac-Man.
 * It contains a binary search tree with all its pixels, as well as its dimensions, offset, and type of figure.
 * It overrides the methods in GraphicalFigureADT.
 * @author Sam
 *
 */
public class GraphicalFigure implements GraphicalFigureADT {
	
	/**
	 * Instance variables
	 */
	private BinarySearchTree bst;
	private String type;
	private int width;
	private int height;
	private int id;
	private Location offset;
	
	/**
	 * Constructor method creates an empty binary search tree and initializes its instance variables.
	 * @param id
	 * @param width
	 * @param height
	 * @param type
	 * @param pos
	 */
	public GraphicalFigure(int id, int width, int height, String type, Location pos) {
		// Initialize variables and create a new tree
		BinarySearchTree bst = new BinarySearchTree();
		this.bst = bst;
		
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.offset = pos;
	}
	
	/**
	 * Set the type of the graphical figure to the specified type.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Return the type of graphical figure.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Return the identifier of this figure.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Return the width of this figure's enclosed rectangle.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Return the height of this figure's enclosed rectangle.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Return the location of this figure's offset to the top-left corner of the game window.
	 */
	public Location getOffset() {
		return offset;
	}
	
	/**
	 * Set this figure's offset to the specified value.
	 */
	public void setOffset(Location value) {
		this.offset = value;
	}
	
	/**
	 * Add a pixel of this graphical figure to its binary search tree.
	 * Handles an exception in case a duplicate insert is attempted.
	 */
	public void addPixel(Pixel pix) throws DuplicatedKeyException {
		try {
			bst.put(bst.getRoot(), pix);	// try adding pixel to the tree
			
		} catch (DuplicatedKeyException e) {
				new DuplicatedKeyException();
		}
	}
	
	/**
	 * Method returns true if this figure intersects the other figure specified in the parameter; false otherwise.
	 * @param Another graphical figure, fig
	 * @return
	 */
	public boolean intersects(GraphicalFigure fig) {
		
		// Check if the figure intersects with the outer bounds.
		boolean checkX = offset.xCoord() < fig.getOffset().xCoord() + fig.getWidth() && fig.getOffset().xCoord() < offset.xCoord() + width;
		boolean checkY = offset.yCoord() < fig.getOffset().yCoord() + fig.getHeight() && fig.getOffset().yCoord() < offset.yCoord() + height;
		if (checkX && checkY) 
			return true;
		
		// Check if any pixel of this enclosed rectangle intersects with another figure's pixel.
		try {
			// Create references to the smallest and largest node.
			Pixel current = bst.smallest(bst.getRoot());
			Pixel last = bst.largest(bst.getRoot());
			
			// Loop from the smallest to largest node, checking if each pixel intersects with one from other other figure.
			while (current != last) {
				
				// For each pixel, determine the intersection coordinates that is not allowed.
				int x = current.getLocation().xCoord() + offset.xCoord() - fig.getOffset().xCoord();
				int y = current.getLocation().yCoord() + offset.yCoord() - fig.getOffset().yCoord();
				Location overlap = new Location(x,y);
				
				// If this coordinate can be found in the other figure, then an intersection occurs. Return false.
				if (fig.bst.get(fig.bst.getRoot(), overlap) != null)
					return true;
				
				// If not, traverse to the next successive node.
				else current = bst.successor(bst.getRoot(), current.getLocation());
			}
			
		} catch (EmptyTreeException e) {
			System.out.println("Tree is Empty.");
			return false;
		}
		
		// If the figure is within the border and the pixels don't intersect with another figure, then return false.
		return false;	
	}
}
