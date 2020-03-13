import java.io.*;
import java.util.Iterator;
import java.util.Stack;

/**
 * Roadmap uses a graph implementation to express a series of roads, which we intend to traverse to reach the destination.
 * @author Sam
 *
 */
public class RoadMap {
	
	/**
	 * Declare instance variables
	 */
	private Graph map;
	int startingNode;
	int destinationNode;
	int width;
	int length;
	int initialMoney;
	int toll;
	int gain;
	
	// Global solution path stack. This way the helper method can push to this directly.
	Stack<Node> solution = new Stack<Node>();
	
	/**
	 * Constructor method reads in a file and creates a graph from its data.
	 * @param inputFile
	 * @throws MapException: if no file is actually passed in.
	 */
	public RoadMap(String inputFile) throws MapException {
		try {
			BufferedReader input = new BufferedReader(new FileReader(inputFile));
			
			// Throw out the first line of the input because we do not need the scale
			Integer.parseInt(input.readLine());
			
			// Initialize the map's instance variables
			this.startingNode = Integer.parseInt(input.readLine());
			this.destinationNode = Integer.parseInt(input.readLine());
			this.width = Integer.parseInt(input.readLine());
			this.length = Integer.parseInt(input.readLine());
			this.initialMoney = Integer.parseInt(input.readLine());
			this.toll = Integer.parseInt(input.readLine());
			this.gain = Integer.parseInt(input.readLine());
			
			this.map = new Graph(width*length);
			
			// These will be used to traverse and track the characters
			String line;
			int row = 0;
			
			while ((line = input.readLine()) != null) {
				
				// Look at every character in the line
				for (int i = 0; i < line.length(); i++) {
					
					// If the row is even, the edges are joined horizontally
					// We don't deal with X's because they are not considered an edge
					if (row%2 == 0) {
						if (line.charAt(i) == 'T')
							map.insertEdge(map.getNode((i-1)/2 + (row/2)*width), map.getNode((i+1)/2 + (row/2)*width), 1);
						else if (line.charAt(i) == 'F')
							map.insertEdge(map.getNode((i-1)/2 + (row/2)*width), map.getNode((i+1)/2 + (row/2)*width), 0);
						else if (line.charAt(i) == 'C')
							map.insertEdge(map.getNode((i-1)/2 + (row/2)*width), map.getNode((i+1)/2 + (row/2)*width), -1);
					}
					
					// If the row is odd, the edges are joined vertically
					else {
						if (line.charAt(i) == 'T')
							map.insertEdge(map.getNode((i/2) + ((row-1)/2)*width), map.getNode((i/2) + ((row+1)/2)*width), 1);
						else if (line.charAt(i) == 'F')
							map.insertEdge(map.getNode((i/2) + ((row-1)/2)*width), map.getNode((i/2) + ((row+1)/2)*width), 0);
						else if (line.charAt(i) == 'C')
							map.insertEdge(map.getNode((i/2) + ((row-1)/2)*width), map.getNode((i/2) + ((row+1)/2)*width), -1);
					}
				}
				
				row++;	// increase the counter
			}
			
			input.close();
			
		} catch (IOException | GraphException e) {
			throw new MapException("Error: file does not exist");
		} 
	}
	
	/**
	 * Returns the graph that represents the road map.
	 * @return
	 */
	public Graph getGraph() {
		return map;
	}
	
	/**
	 * Returns the name of the starting node
	 * @return
	 */
	public int getStartingNode() {
		return startingNode;
	}
	
	/**
	 * Returns the name of the destination node
	 * @return
	 */
	public int getDestinationNode() {
		return destinationNode;
	}
	
	/**
	 * Return the initial amount of money.
	 * @return
	 */
	public int getInitialMoney() {
		return initialMoney;
	}
	
	/**
	 * Returns a Java Iterator containing the nodes traversed from the path from the starting node to the destination node.
	 * @param start
	 * @param destination
	 * @param initialMoney
	 * @return
	 */
	public Iterator<Node> findPath(int start, int destination, int initialMoney) {
	
		try {
			// Call the helper method to determine if a path to the destination exists.
			// If so, return an iterator of the solution stack.
			if (path(map.getNode(start), map.getNode(destination), initialMoney) == true)
				return solution.iterator();
			
		} catch (GraphException e) {
			e.printStackTrace();
		}
		
		// If no path can be found, return null.
		return null;
	}
	
	// Helper Methods
	
	/**
	 * Standard DFS algorithm to find a path from a current node to the destination.
	 * It will consider how much money is available as well.
	 * Returns true if a path can be found.
	 * @param current
	 * @param end
	 * @param money
	 * @return
	 */
	private boolean path(Node current, Node end, int money) {
		
		try {
			// Mark the current node and push it on the solution stack
			current.setMark(true);
			solution.push(current);
			
			// If the current node is the destination, the path is complete and true is returned.
			if (solution.peek().getName() == end.getName())
				return true;
			
			else {
				// Iterate through all the incident edges on the current node
				Iterator<Edge> potentialPaths = map.incidentEdges(current);
				while (potentialPaths.hasNext()) {
					// Store the edge and adjacent node data
					Edge edge = potentialPaths.next();
					Node adj = edge.secondEndpoint();
					
					// Do not consider nodes that have already been marked
					if (!adj.getMark()) {
						
						// If the edge is a toll road, call the path function but with the payment taken out.
						if (edge.getType() == 1 && (money - toll >= 0)) {
							//adj.setMark(true);
							if (path(adj, end, money-toll) == true)
								return true;
						}
						
						// If the edge is a normal road, call the path function on the adjacent node
						else if (edge.getType() == 0) {
							//adj.setMark(true);
							if (path(adj, end, money) == true)
								return true;
						}
						
						// If the edge is a gain road, call the path function with money increased
						else if (edge.getType() == -1) {
							//adj.setMark(true);
							if (path(adj, end, money+gain) == true)
								return true;
						}
					}
				}
				
				// If none of the current's nodes lead to a solution, un-mark it and pop it off the stack.
				solution.pop();
				current.setMark(false);
				return false;
			}
			
		} catch (Exception e) {
			
		}
		return false;
		
	}
}
