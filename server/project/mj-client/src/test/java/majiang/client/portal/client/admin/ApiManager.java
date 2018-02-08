package majiang.client.portal.client.admin;

import org.forkjoin.apikit.client.HttpClientAdapter;

import majiang.client.portal.client.admin.api.AccountApi;
import majiang.client.portal.client.admin.api.RoomApi;
import majiang.client.portal.client.admin.api.SettingApi;
import majiang.client.portal.client.admin.api.UserApi;

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
	public final AccountApi accountApi;
	/**
	 * @author  zuoge85 on 15/6/11.
	 */
	public final RoomApi roomApi;
	/**
	 * @author  zuoge85 on 15/6/11.
	 */
	public final SettingApi settingApi;
	/**
	 * @author  zuoge85 on 15/6/11.
	 */
	public final UserApi userApi;

	public ApiManager(HttpClientAdapter httpClientAdapter) {
		this.httpClientAdapter = httpClientAdapter;

		accountApi = new AccountApi(httpClientAdapter);
		roomApi = new RoomApi(httpClientAdapter);
		settingApi = new SettingApi(httpClientAdapter);
		userApi = new UserApi(httpClientAdapter);
	}
}
