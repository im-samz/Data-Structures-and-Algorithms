/**
 * Exception class for when a node with the same key exists during put()
 * @author Sam
 *
 */
public class DuplicatedKeyException extends RuntimeException {
	
	public DuplicatedKeyException() {
		super("Error: key already exists in dictionary.\n");
	}
}
