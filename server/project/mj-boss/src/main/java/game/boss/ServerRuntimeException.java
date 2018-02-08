package game.boss;

public class ServerRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8697426120969322468L;

	public ServerRuntimeException() {
	}

	public ServerRuntimeException(String message) {
		super(message);
	}

	public ServerRuntimeException(Throwable cause) {
		super(cause);
	}

	public ServerRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public static final void checkOverflowInt(int v){
		if(v<0){
			throw new ServerRuntimeException("不接受负值");
		}
	}
	
	public static final void checkOverflowLong(long v){
		if(v<0){
			throw new ServerRuntimeException("不接受负值");
		}
	}

}
