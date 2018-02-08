package game.gateway;

public class GatewayException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 344999288729171733L;

	public GatewayException() {
		super();
	}

	public GatewayException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GatewayException(String message, Throwable cause) {
		super(message, cause);
	}

	public GatewayException(String message) {
		super(message);
	}

	public GatewayException(Throwable cause) {
		super(cause);
	}

}
