/**
 * Edge class represents the edge of a graph, connecting two nodes.
 * @author Sam
 *
 */
public class Edge {
	
	/**
	 * Declare instance variables.
	 */
	private Node firstEndpoint;
	private Node secondEndpoint;
	private int roadType;
	
	/**
	 * Constructor takes in the nodes on both endpoints and the type of road this edge represents.
	 * @param u
	 * @param v
	 * @param type
	 */
	public Edge(Node u, Node v, int type) {
		this.firstEndpoint = u;
		this.secondEndpoint = v;
		this.roadType = type;
	}
	
	/**
	 * Return the Node on the first endpoint.
	 * @return
	 */
	public Node firstEndpoint() {
		return firstEndpoint;
	}
	
	/**
	 * Return the Node on the second endpoint.
	 * @return
	 */
	public Node secondEndpoint() {
		return secondEndpoint;
	}
	
	/**
	 * Return the type of road.
	 * 0 = public road
	 * 1 = private road
	 * -1 = reward road
	 * @return
	 */
	public int getType() {
		return roadType;
	}
}

 