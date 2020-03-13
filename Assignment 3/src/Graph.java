import java.util.*;

/**
 * Graph class implements the methods in GraphADT.
 * Uses an adjacency matrix to keep track of edges.
 * @author Sam
 *
 */
public class Graph implements GraphADT {
	
	/**
	 * Declare Instance variables.
	 */
	Node[] nodeArray;
	Edge[][] adjMatrix;
	int size;
	
	/**
	 * Constructor method creates a node array of size n and its corresponding adjacency matrix. Initializes each of them too.
	 * @param n
	 */
	public Graph(int n) {
		// Set the size of the array to the total number of nodes
		nodeArray = new Node[n];
		// Set the size of the adjacency matrix
		adjMatrix = new Edge[n][n];
		this.size = n;
		
		// Create a node for each position in the array
		for (int i = 0; i<n; i++) {
			nodeArray[i] = new Node(i);
		}
		
		// unless it is already set to null...
		// Create an empty matrix with all edges set to null
		for (int i = 0; i<n; i++) {
			for (int j = 0; j<n; j++) {
				adjMatrix[i][j] = null;
			}
		}
	}
	
	/**
	 * Returns the node with the specified name.
	 * @param name
	 * @return
	 * @throws GraphException: If no such node exists.
	 */
	public Node getNode(int name) throws GraphException {
		try {
			return nodeArray[name];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new GraphException("Error: No such node exists");
		}
	}
	
	/**
	 * Adds an edge of the given type connecting u and v
	 * @param u
	 * @param v
	 * @param edgeType
	 * @throws GraphException: if either node does not exist or if there is already an edge connecting the given nodes.
	 */
	public void insertEdge(Node u, Node v, int edgeType) throws GraphException {
		if (!containsNode(u, v))
			throw new GraphException("Error: No such node exists");
		
		else {
			// If no edge exists yet, create an edge joining the first-second node and second-first node
			if (adjMatrix[u.getName()][v.getName()] == null) {
				adjMatrix[u.getName()][v.getName()] = new Edge(u, v, edgeType);
				adjMatrix[v.getName()][u.getName()] = new Edge(v, u, edgeType);	
			}
			
			else throw new GraphException("Error: Edge already exists");	// If an edge between these nodes already exists
		}
	}		
	
	/**
	 * Returns a Java Iterator storing all the edges incident on node u.
	 * Null if u has no incident edges.
	 * @param u
	 * @return
	 */
	public Iterator<Edge> incidentEdges(Node u) throws GraphException {
		if (!containsNode(u))
			throw new GraphException("Error: node is not in the graph");
		
		else {
			// Create a stack object, which we imported
			Stack<Edge> stack = new Stack<Edge>();
			// Go through the Node's row. If you come across an edge, push it to the stack
			for (int i = 0; i < size; i++) {
				if (adjMatrix[u.getName()][i] != null)
					stack.push(adjMatrix[u.getName()][i]);
			}
			return stack.iterator();	// return the edge iterator
		}
	}
	
	/**
	 * Returns the edge object connecting u and v.
	 * @param u
	 * @param v
	 * @return
	 * @throws GraphException: if no such edge exists yet.
	 */
	public Edge getEdge(Node u, Node v) throws GraphException {
		if (!containsNode(u, v))
			throw new GraphException("Error: no such node exists");
		
		else {
			if (adjMatrix[u.getName()][v.getName()] != null)
				return adjMatrix[u.getName()][v.getName()];
			else throw new GraphException("Error: no such edge exists");
		}
	}
	
	/**
	 * Returns true if nodes u and v are adjacent; false otherwise.
	 * @param u
	 * @param v
	 * @return
	 */
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		if (!containsNode(u, v))
			throw new GraphException("Error: no such node exists");
		
		else {
			if (adjMatrix[u.getName()][v.getName()] != null)
				return true;
			else return false;
		}
	}
	
	// Private Methods
	
	/**
	 * Checks if nodes u and v are in the graph.
	 * If it can successfully access the node, then it was initialized and is in the graph.
	 * Return false otherwise.
	 */
	private boolean containsNode(Node u, Node v) {
		try {
			getNode(u.getName());
			getNode(v.getName());
			return true;
		} catch (GraphException e) {
			return false;
		}
	}
	
	/**
	 * Checks if a single node, u, in in the graph.
	 * Overrides containsNode, so it uses the same logic.
	 */
	private boolean containsNode(Node u) {
		try {
			getNode(u.getName());
			return true;
		} catch (GraphException e) {
			return false;
		}
	}

}
