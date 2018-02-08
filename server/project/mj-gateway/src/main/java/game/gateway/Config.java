package game.gateway;

public class Config {
	private int port;
	private int maxConnect;
	public final int getPort() {
		return port;
	}
	public final void setPort(int port) {
		this.port = port;
	}
	public final int getMaxConnect() {
		return maxConnect;
	}
	public final void setMaxConnect(int maxConnect) {
		this.maxConnect = maxConnect;
	}
	@Override
	public String toString() {
		return "Config [port=" + port + ", maxConnect=" + maxConnect
				+ "]";
	}
}
