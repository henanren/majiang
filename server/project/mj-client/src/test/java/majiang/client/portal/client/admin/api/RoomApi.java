package majiang.client.portal.client.admin.api;

import majiang.client.portal.client.admin.model.PageModel;
import majiang.client.portal.client.admin.model.RoomModel;
import majiang.client.services.AdminRoomService;
import org.forkjoin.apikit.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.Future;
import java.util.Map.Entry;

import org.forkjoin.apikit.client.*;

/**
 * @author  zuoge85 on 15/6/11.
 */
public class RoomApi {
	private HttpClientAdapter httpClientAdapter;

	public RoomApi(HttpClientAdapter httpClientAdapter) {
		this.httpClientAdapter = httpClientAdapter;
	}

	/**
	 * 测试
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>room/list/{page}/{pageSize}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int page</li>
	 * <li><b>PathVariable:</b> int pageSize</li>
	 * <li><b>Model:</b> PageModel&lt;RoomModel&gt;</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see PageModel&lt;RoomModel&gt;
	 * @see int
	 * @see int

	 */
	public PageModel<RoomModel> listData(int page, int pageSize) {
		Result<PageModel<RoomModel>> result = list(page, pageSize);
		if (!result.isSuccess()) {
			Exception ex = result.getException();
			if (ex != null) {
				throw new RuntimeException(ex.getMessage(), ex);
			} else {
				throw new RuntimeException(result.toString());
			}
		}
		return result.getData();
	}

	/**
	 * 测试
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>room/list/{page}/{pageSize}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int page</li>
	 * <li><b>PathVariable:</b> int pageSize</li>
	 * <li><b>Model:</b> PageModel&lt;RoomModel&gt;</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see PageModel&lt;RoomModel&gt;
	 * @see int
	 * @see int

	 */
	public Result<PageModel<RoomModel>> list(int page, int pageSize) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("page", page);
		_uriVariables.put("pageSize", pageSize);
		String _url = ApiUtils.expandUriComponent("room/list/{page}/{pageSize}", _uriVariables);

		return httpClientAdapter.request("GET", _url, null, _0Type, true);
	}

	/**
	 * 测试
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>room/list/{page}/{pageSize}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int page</li>
	 * <li><b>PathVariable:</b> int pageSize</li>
	 * <li><b>Model:</b> PageModel&lt;RoomModel&gt;</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see PageModel&lt;RoomModel&gt;
	 * @see int
	 * @see int

	 */
	public Future<?> list(int page, int pageSize, Callback<PageModel<RoomModel>> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("page", page);
		_uriVariables.put("pageSize", pageSize);
		String _url = ApiUtils.expandUriComponent("room/list/{page}/{pageSize}", _uriVariables);

		return httpClientAdapter.requestAsync("GET", _url, null, _0Type, true, callable);
	}

	private static final ApiType _0Type = ApiUtils.type(Result.class,
			ApiUtils.type(PageModel.class, ApiUtils.type(RoomModel.class)));
}