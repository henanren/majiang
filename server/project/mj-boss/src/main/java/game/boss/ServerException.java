package game.boss;

public class ServerException  extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -194935531254942082L;

	public ServerException() {
		super();
	}

	public ServerException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerException(String message) {
		super(message);
	}

	public ServerException(Throwable cause) {
		super(cause);
	}
}
