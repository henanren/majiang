package majiang.client.portal.client.admin.api;

import majiang.client.portal.client.admin.form.SettingForm;
import majiang.client.portal.client.admin.model.SettingModel;
import majiang.client.services.AdminSettingService;
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
public class SettingApi {
	private HttpClientAdapter httpClientAdapter;

	public SettingApi(HttpClientAdapter httpClientAdapter) {
		this.httpClientAdapter = httpClientAdapter;
	}

	/**
	 * 测试
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>setting</b>
	 * <ul>
	 * <li><b>Model:</b> SettingModel</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see SettingModel

	 */
	public SettingModel getData() {
		Result<SettingModel> result = get();
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>setting</b>
	 * <ul>
	 * <li><b>Model:</b> SettingModel</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see SettingModel

	 */
	public Result<SettingModel> get() {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("setting", _uriVariables);

		return httpClientAdapter.request("GET", _url, null, _0Type, true);
	}

	/**
	 * 测试
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>setting</b>
	 * <ul>
	 * <li><b>Model:</b> SettingModel</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see SettingModel

	 */
	public Future<?> get(Callback<SettingModel> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("setting", _uriVariables);

		return httpClientAdapter.requestAsync("GET", _url, null, _0Type, true, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>setting</b>
	 * <ul>
	 * <li><b>Form:</b>SettingFormchange</li>
	 * <li><b>Model:</b> SettingModel</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see SettingModel
	 * @see SettingForm

	 */
	public SettingModel changeData(SettingForm form) {
		Result<SettingModel> result = change(form);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>setting</b>
	 * <ul>
	 * <li><b>Form:</b>SettingFormchange</li>
	 * <li><b>Model:</b> SettingModel</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see SettingModel
	 * @see SettingForm

	 */
	public Result<SettingModel> change(SettingForm form) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("setting", _uriVariables);

		List<Entry<String, Object>> _form = form.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.request("POST", _url, _form, _1Type, true);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>setting</b>
	 * <ul>
	 * <li><b>Form:</b>SettingFormchange</li>
	 * <li><b>Model:</b> SettingModel</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see SettingModel
	 * @see SettingForm

	 */
	public Future<?> change(SettingForm form, Callback<SettingModel> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("setting", _uriVariables);

		List<Entry<String, Object>> _form = form.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.requestAsync("POST", _url, _form, _1Type, true, callable);
	}

	private static final ApiType _0Type = ApiUtils.type(Result.class, ApiUtils.type(SettingModel.class));
	private static final ApiType _1Type = ApiUtils.type(Result.class, ApiUtils.type(SettingModel.class));
}