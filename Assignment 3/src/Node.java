/**
 * Node class contains its number and whether it is marked or not. Will be used to build up the graph.
 * @author Sam
 *
 */
public class Node {
	
	/**
	 * Declare instance variables.
	 */
	private int name;
	private boolean marked;
	
	/**
	 * Constructor method takes in a integer value and sets it as the node's name.
	 * @param name
	 */
	public Node(int name) {
		this.name = name;
	}
	
	/**
	 * Sets the node's mark to either true or false.
	 * @param mark
	 */
	public void setMark(boolean mark) {
		this.marked = mark;
	}
	
	/**
	 * Returns the node's mark.
	 * @return
	 */
	public boolean getMark() {
		return marked;
	}
	
	/**
	 * Returns the node's name.
	 * @return
	 */
	public int getName() {
		return name;
	}
}
