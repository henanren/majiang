package majiang.client.portal.client.admin.form;

import org.forkjoin.apikit.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

/**

 */
public class LoginForm implements ApiMessage {

	private String name;

	private String password;

	public LoginForm() {
	}

	public LoginForm(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public List<Entry<String, Object>> encode(String parent, List<Entry<String, Object>> $list) {

		if (name != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "name", name));
		}

		if (password != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "password", password));
		}

		return $list;
	}

	@Override
	public String toString() {
		return "LoginForm [name=" + name + ",password=" + password + ", ]";
	}
}