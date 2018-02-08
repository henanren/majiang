package majiang.client.portal.client.admin.api;

import game.boss.dao.entity.UserDO;
import majiang.client.portal.client.admin.form.PayForm;
import majiang.client.portal.client.admin.model.PageModel;
import majiang.client.portal.client.admin.model.UserModel;
import majiang.client.services.AdminUserService;
import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.spring.I18nValidationException;
import org.springframework.beans.BeanUtils;
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
public class UserApi {
	private HttpClientAdapter httpClientAdapter;

	public UserApi(HttpClientAdapter httpClientAdapter) {
		this.httpClientAdapter = httpClientAdapter;
	}

	/**
	 * 测试
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>user/list/{page}/{pageSize}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int page</li>
	 * <li><b>PathVariable:</b> int pageSize</li>
	 * <li><b>Model:</b> PageModel&lt;UserModel&gt;</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see PageModel&lt;UserModel&gt;
	 * @see int
	 * @see int

	 */
	public PageModel<UserModel> listData(int page, int pageSize) {
		Result<PageModel<UserModel>> result = list(page, pageSize);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>user/list/{page}/{pageSize}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int page</li>
	 * <li><b>PathVariable:</b> int pageSize</li>
	 * <li><b>Model:</b> PageModel&lt;UserModel&gt;</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see PageModel&lt;UserModel&gt;
	 * @see int
	 * @see int

	 */
	public Result<PageModel<UserModel>> list(int page, int pageSize) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("page", page);
		_uriVariables.put("pageSize", pageSize);
		String _url = ApiUtils.expandUriComponent("user/list/{page}/{pageSize}", _uriVariables);

		return httpClientAdapter.request("GET", _url, null, _0Type, true);
	}

	/**
	 * 测试
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>user/list/{page}/{pageSize}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int page</li>
	 * <li><b>PathVariable:</b> int pageSize</li>
	 * <li><b>Model:</b> PageModel&lt;UserModel&gt;</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see PageModel&lt;UserModel&gt;
	 * @see int
	 * @see int

	 */
	public Future<?> list(int page, int pageSize, Callback<PageModel<UserModel>> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("page", page);
		_uriVariables.put("pageSize", pageSize);
		String _url = ApiUtils.expandUriComponent("user/list/{page}/{pageSize}", _uriVariables);

		return httpClientAdapter.requestAsync("GET", _url, null, _0Type, true, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>user/{userId}/level/{level}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int userId</li>
	 * <li><b>PathVariable:</b> int level</li>
	 * <li><b>Model:</b> void</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see int
	 * @see int

	 */
	public Void changelevelData(int userId, int level) {
		Result<Void> result = changelevel(userId, level);
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
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>user/{userId}/level/{level}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int userId</li>
	 * <li><b>PathVariable:</b> int level</li>
	 * <li><b>Model:</b> void</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see int
	 * @see int

	 */
	public Result<Void> changelevel(int userId, int level) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("userId", userId);
		_uriVariables.put("level", level);
		String _url = ApiUtils.expandUriComponent("user/{userId}/level/{level}", _uriVariables);

		return httpClientAdapter.request("POST", _url, null, _1Type, true);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>user/{userId}/level/{level}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int userId</li>
	 * <li><b>PathVariable:</b> int level</li>
	 * <li><b>Model:</b> void</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see int
	 * @see int

	 */
	public Future<?> changelevel(int userId, int level, Callback<Void> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("userId", userId);
		_uriVariables.put("level", level);
		String _url = ApiUtils.expandUriComponent("user/{userId}/level/{level}", _uriVariables);

		return httpClientAdapter.requestAsync("POST", _url, null, _1Type, true, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>user/pay</b>
	 * <ul>
	 * <li><b>Form:</b>PayFormpay</li>
	 * <li><b>Model:</b> UserModel</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see UserModel
	 * @see PayForm

	 */
	public UserModel payData(PayForm form) {
		Result<UserModel> result = pay(form);
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
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>user/pay</b>
	 * <ul>
	 * <li><b>Form:</b>PayFormpay</li>
	 * <li><b>Model:</b> UserModel</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see UserModel
	 * @see PayForm

	 */
	public Result<UserModel> pay(PayForm form) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("user/pay", _uriVariables);

		List<Entry<String, Object>> _form = form.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.request("POST", _url, _form, _2Type, true);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>user/pay</b>
	 * <ul>
	 * <li><b>Form:</b>PayFormpay</li>
	 * <li><b>Model:</b> UserModel</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see UserModel
	 * @see PayForm

	 */
	public Future<?> pay(PayForm form, Callback<UserModel> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("user/pay", _uriVariables);

		List<Entry<String, Object>> _form = form.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.requestAsync("POST", _url, _form, _2Type, true, callable);
	}

	private static final ApiType _0Type = ApiUtils.type(Result.class,
			ApiUtils.type(PageModel.class, ApiUtils.type(UserModel.class)));
	private static final ApiType _1Type = ApiUtils.type(Result.class, ApiUtils.type(Void.class));
	private static final ApiType _2Type = ApiUtils.type(Result.class, ApiUtils.type(UserModel.class));
}