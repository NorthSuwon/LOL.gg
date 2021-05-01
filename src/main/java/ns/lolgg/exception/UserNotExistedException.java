package ns.lolgg.exception;

public class UserNotExistedException extends RuntimeException {
	
	public UserNotExistedException(String user_id) {
		super("USER ID is not existed: "+ user_id);
	}
}
