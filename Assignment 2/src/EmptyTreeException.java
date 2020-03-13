/**
 * Exception class for the case when the tree is empty. Used in smallest() and largest()
 * @author Sam
 *
 */
public class EmptyTreeException extends RuntimeException {

	public EmptyTreeException() {
		super("Error: the tree is empty.\n");
	}
}
