/**
 * BinarySearchTree class contains nodes that contain the pixels of a graphical figure.
 * @author Sam
 *
 */
public class BinarySearchTree {
	
	/**
	 * Instance variables.
	 */
	private BinaryNode root = null;
	
	/**
	 * Constructor method creates an empty binary search tree.
	 */
	public BinarySearchTree() {
		BinaryNode root = new BinaryNode();
		this.root = root;
	}
	
	/**
	 * Method returns the pixel stored in the BST by the key.
	 * @param root node, r
	 * @param key
	 * @return Corresponding Pixel object; null if not found.
	 */
	public Pixel get(BinaryNode r, Location key) {
		// Base case
		if (r.isLeaf())
			return r.getData();
		
		// Compare the keys and traverse accordingly.
		else {
			if (key.compareTo(r.getData().getLocation()) == 0)
				return r.getData();
			
			else if (key.compareTo(r.getData().getLocation()) < 0)
				return get(r.getLeft(), key);
			
			else return get(r.getRight(), key);
		}
	}
	
	/**
	 * Method inserts a new node storing the pixel data.
	 * @param root node, r.
	 * @param data to be inserted, data
	 * @throws DuplicatedKeyException: if data already exists.
	 */
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
		// Traverse to the position where the data should be stored
		BinaryNode current = getNode(r, data.getLocation());
		
		// If this position is on an internal node, it means that the data already exists. Throw an exception.
		if (!current.isLeaf())
			throw new DuplicatedKeyException();
		
		// If the position is a leaf node.
		else {
			current.setData(data);	// set the data
			
			// Create new leaf nodes and set their parents to current.
			current.setLeft(new BinaryNode(null, null, null, current));
			current.setRight(new BinaryNode(null, null, null, current));
		}
	}
	
	
	/**
	 * Method removes a node and its data from the binary search tree.
	 * @param root node, r
	 * @param key
	 * @throws InexistentKeyException: if no node contains the same key.
	 */
	
	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
		// Traverse the tree to the position of the node to be deleted.
		BinaryNode current = getNode(r, key);
		
		// If the key is non-existent, there will be no corresponding internal node.
		if (current.isLeaf() || current == null)
			throw new InexistentKeyException();
		
		// Case if the node to be deleted has a child that is a leaf
		else if (current.getLeft().isLeaf() || current.getRight().isLeaf()) {
			
			// Case if the node to be deleted is the root.
			if (root == current) {
				
				// If the left sub-branch is a leaf, set the new root to be the right child.
				if (root.getLeft() == null)
					root = current.getRight();
				
				// Same logic, but for the other side.
				else
					root = current.getLeft();
				
				current = null;					// delete the node
			}
			
			// Case if an internal node has one leaf child. Also works if both children are leaves.
			else {
				
				// If the left child is null, determine if the node to be deleted is the left or right child of its parent.
				// Replace the node's relationship with its right child.
				BinaryNode parent = current.getParent();
				if (current.getLeft().isLeaf()) {
					if (parent.getLeft() == current) {
						parent.setLeft(current.getRight());
						current.getRight().setParent(parent);
					}
					else {
						parent.setRight(current.getRight());
						current.getRight().setParent(parent);
					}
				}
				
				// If the right child is null, do the same as above, but replace with left child.
				else if (current.getRight().isLeaf()){
					if (current.getParent().getLeft() == current) {
						parent.setLeft(current.getLeft());
						current.getLeft().setParent(parent);
					}
						
					else {
						parent.setRight(current.getLeft());
						current.getLeft().setParent(parent);
					}
				}
			}
		}
			
		// Case if the node is an internal node with no leaves as children.
		// Replace the node with its right child to preserve BST properties and remove the right child recursively.
		else {
			BinaryNode smallest = smallestNode(current.getRight());
			current.setData(smallest.getData());
			remove(smallest, smallest.getData().getLocation());
		}
		
	}

	/**
	 * Method finds the smallest value in the tree that is larger than the key.
	 * @param root node, r
	 * @param key
	 * @return the successor
	 */
	public Pixel successor(BinaryNode r, Location key) {
		// If the tree is empty return null.
		if (r.isLeaf())
			return null;
		
		else {
			// Access the node that we will find the successor of
			BinaryNode current = getNode(r, key);
	
			// If neither the node or its right child is a leaf, the next node will be the node with the smallest value in the right child's subtree.
			if (!current.isLeaf() && !current.getRight().isLeaf())
				return smallest(current.getRight());
			
			else if (current == root)
				return current.getData();
			
			// If the current node is a leaf, track up the tree until the node is no longer the right child of its parent.
			else {
				BinaryNode parent = current.getParent();
				while (current != root && parent.getRight() == current) {
					current = parent;
					parent = parent.getParent();
				}
				
				// In the case that the key was the largest Node. This tells the program to return null.
				if (current == root)
					return null;
				
				// Return the parent if the node was the left child because it will always be larger
				else return parent.getData();
			}
		}
	}

	/**
	 * Method finds the largest values in the tree that is smaller than the key.
	 * Uses the same logic, but switches left to right and smallest to largest.
	 * @param root node, r
	 * @param key
	 * @return the predecessor.
	 */
	public Pixel predecessor(BinaryNode r, Location key) {
		
		if (root == null)
			return null;
		
		else {
			BinaryNode current = getNode(r, key);
			
			if (!current.isLeaf() && !current.getLeft().isLeaf()) {		// If the left child is not a leaf take the largest node in its sub-tree
				return largest(current.getLeft());
			}
			
			else {
				BinaryNode parent = current.getParent();
				while (current != root && parent.getLeft() == current) {	// Keep looping until the child is no longer a left child.
					current = parent;
					parent = parent.getParent();
				}
				
				// In the case that the key was the smallest node. The program will return null.
				if (current == root)
					return null; // changed
				
				// The parent of a right child will always be the next smallest.
				else return parent.getData();
			}
		}
	}
	
	/**
	 * Method returns the node with the smallest value in the sub-tree.
	 * @param root node, r
	 * @return Pixel with the smallest value
	 * @throws EmptyTreeException
	 */
	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		// If the tree is empty, there is no smallest value.
		if (root == null)
			throw new EmptyTreeException();
		
		else {
			// Once you are on the last node that contains data, return it.
			if (r.getLeft().isLeaf())
				return r.getData();
			
			// Keep traversing to the left.
			else return smallest(r.getLeft());
		}
	}
	
	/**
	 * Returns the node with the largest value in the sub-tree.
	 * Uses the same logic as smallest, but traverses to the very right.
	 * @param root node, r
	 * @return Pixel with the largest value
	 * @throws EmptyTreeException
	 */
	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		if (root == null)
			throw new EmptyTreeException();
		
		else {
			if (r.getRight().isLeaf())
				return r.getData();
			
			// Traverse to the right.
			else return largest(r.getRight());
		}
	}
	
	public BinaryNode getRoot() {
		return root;
	}
	
	// Helper Methods
	
	/**
	 * Helper function that is exactly the same as get(), but it returns the node rather than its pixel.
	 * @param root node, r
	 * @param key
	 * @return node containing key
	 */
	private BinaryNode getNode(BinaryNode r, Location key) {
		// The leaf node contains null, so if the method reaches all the way down to the leaf node, the key does not exist in all its ancestors.
		if (r.isLeaf())
			return r;
		
		// If the key is not found, the null found in leaf node will be returned.
		else {
			if (key.compareTo(r.getData().getLocation()) == 0)
				return r;
			else if (key.compareTo(r.getData().getLocation()) < 0)
				return getNode(r.getLeft(), key);
			else
				return getNode(r.getRight(), key);
		}
	}
	
	/**
	 * Helper function that returns the smallest node. This is used when removing an internal node.
	 * There is no need to throw an exception because the only time this is used is when it is called during removal.
	 * Obviously, a tree exists in that case.
	 * @param root node, r
	 * @return the smallest node
	 */
	public BinaryNode smallestNode(BinaryNode r) {
		// Once you are on the last node that contains data, return it.
		if (r.getLeft().isLeaf())
			return r;
		
		// Keep traversing to the left.
		else return smallestNode(r.getLeft());
	}
}
