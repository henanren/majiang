package majiang.client.portal.client.agent;

import org.forkjoin.apikit.client.HttpClientAdapter;

import majiang.client.portal.client.agent.api.AgentAccountApi;

/**
 * api 管理器，单例，使用之前需要初始化
 * @author zuoge85 on 15/6/15.
 */
public class ApiManager {
	private static ApiManager instance;

	/**
	 * 初始化
	 */
	public static void init(HttpClientAdapter httpClientAdapter) {
		instance = new ApiManager(httpClientAdapter);
	}

	public static ApiManager getInstance() {
		return instance;
	}

	private HttpClientAdapter httpClientAdapter;

	/**
	 * @author  zuoge85 on 15/6/11.
	 */
	public final AgentAccountApi agentAccountApi;

	public ApiManager(HttpClientAdapter httpClientAdapter) {
		this.httpClientAdapter = httpClientAdapter;

		agentAccountApi = new AgentAccountApi(httpClientAdapter);
	}
}
