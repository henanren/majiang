package majiang.client.portal.client.admin.api;

import majiang.client.portal.AdminAccount;
import majiang.client.portal.client.admin.form.LoginForm;
import majiang.client.portal.client.admin.model.LoginTokenModel;
import majiang.client.services.AdminAccountService;
import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.spring.I18nValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

import java.util.*;
import java.util.concurrent.Future;
import java.util.Map.Entry;

import org.forkjoin.apikit.client.*;

/**
 * @author  zuoge85 on 15/6/11.
 */
public class AccountApi {
	private HttpClientAdapter httpClientAdapter;

	public AccountApi(HttpClientAdapter httpClientAdapter) {
		this.httpClientAdapter = httpClientAdapter;
	}

	/**
	 * 测试
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/login</b>
	 * <ul>
	 * <li><b>Form:</b>LoginFormlogin</li>
	 * <li><b>Form:</b>HttpServletRequestlogin</li>
	 * <li><b>Model:</b> LoginTokenModel</li>
	 * </ul>
	 * </div>
	 * @see LoginTokenModel
	 * @see LoginForm
	 * @see HttpServletRequest

	 */
	public LoginTokenModel loginData(LoginForm form) {
		Result<LoginTokenModel> result = login(form);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/login</b>
	 * <ul>
	 * <li><b>Form:</b>LoginFormlogin</li>
	 * <li><b>Form:</b>HttpServletRequestlogin</li>
	 * <li><b>Model:</b> LoginTokenModel</li>
	 * </ul>
	 * </div>
	 * @see LoginTokenModel
	 * @see LoginForm
	 * @see HttpServletRequest

	 */
	public Result<LoginTokenModel> login(LoginForm form) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("account/login", _uriVariables);

		List<Entry<String, Object>> _form = form.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.request("GET", _url, _form, _0Type, false);
	}

	/**
	 * 测试
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/login</b>
	 * <ul>
	 * <li><b>Form:</b>LoginFormlogin</li>
	 * <li><b>Form:</b>HttpServletRequestlogin</li>
	 * <li><b>Model:</b> LoginTokenModel</li>
	 * </ul>
	 * </div>
	 * @see LoginTokenModel
	 * @see LoginForm
	 * @see HttpServletRequest

	 */
	public Future<?> login(LoginForm form, Callback<LoginTokenModel> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("account/login", _uriVariables);

		List<Entry<String, Object>> _form = form.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.requestAsync("GET", _url, _form, _0Type, false, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/test</b>
	 * <ul>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String

	 */
	public String testData() {
		Result<String> result = test();
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/test</b>
	 * <ul>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String

	 */
	public Result<String> test() {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("account/test", _uriVariables);

		return httpClientAdapter.request("GET", _url, null, _1Type, false);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/test</b>
	 * <ul>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String

	 */
	public Future<?> test(Callback<String> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("account/test", _uriVariables);

		return httpClientAdapter.requestAsync("GET", _url, null, _1Type, false, callable);
	}

	private static final ApiType _0Type = ApiUtils.type(Result.class, ApiUtils.type(LoginTokenModel.class));
	private static final ApiType _1Type = ApiUtils.type(Result.class, ApiUtils.type(String.class));
}