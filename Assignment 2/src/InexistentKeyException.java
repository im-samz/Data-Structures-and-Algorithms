/**
 * Exception class for the case when no key exists during remove()
 * @author Sam
 *
 */
public class InexistentKeyException extends RuntimeException {
	
	public InexistentKeyException() {
		super("Error: key does not exist in dictionary.\n");
	}
}
