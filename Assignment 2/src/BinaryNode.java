/**
 * Binary Node class contains Pixel objects and compose the Binary Search Tree class.
 * @author Sam
 *
 */
public class BinaryNode {
	
	/**
	 * Instance variables
	 */
	private Pixel data;
	private BinaryNode left;
	private BinaryNode right;
	private BinaryNode parent;
	
	/**
	 * Constructor initializes all the instance variables.
	 * @param value
	 * @param left
	 * @param right
	 * @param parent
	 */
	public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
		this.data = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	/**
	 * Empty constructor creates a leaf node.
	 */
	public BinaryNode() {
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	
	/**
	 * Getter method returns the parent.
	 * @return
	 */
	public BinaryNode getParent() {
		return parent;
	}
	
	/**
	 * Setter method sets the parent.
	 * @param parent
	 */
	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}
	
	/**
	 * Set the left child to the node, p.
	 * @param p
	 */
	public void setLeft(BinaryNode p) {
		this.left = p;
	}
	
	/**
	 * Set the right child to the node, p.
	 * @param p
	 */
	public void setRight(BinaryNode p) {
		this.right = p;
	}
	
	/**
	 * Set the data to the pixel, value.
	 * @param value
	 */
	public void setData(Pixel value) {
		this.data = value;
	}
	
	/**
	 * Check if the node is leaf by checking if the left and right child are null or not.
	 * @return
	 */
	public boolean isLeaf() {
		if (this.left == null && this.right == null)
			return true;
		else return false;
	}
	
	/**
	 * Getter method returns the node's data.
	 * @return
	 */
	public Pixel getData() {
		return this.data;
	}
	
	/**
	 * Getter method returns the node's left child.
	 * @return
	 */
	public BinaryNode getLeft() {
		return this.left;
	}
	
	/**
	 * Getter method returns the node's right child.
	 * @return
	 */
	public BinaryNode getRight() {
		return this.right;
	}
}
