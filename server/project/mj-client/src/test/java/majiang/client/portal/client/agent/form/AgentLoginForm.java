package majiang.client.portal.client.agent.form;

import org.forkjoin.apikit.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

/**

 */
public class AgentLoginForm implements ApiMessage {

	private int id;

	private String password;

	public AgentLoginForm() {
	}

	public AgentLoginForm(int id, String password) {
		this.id = id;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public List<Entry<String, Object>> encode(String parent, List<Entry<String, Object>> $list) {

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "id", id));

		if (password != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "password", password));
		}

		return $list;
	}

	@Override
	public String toString() {
		return "AgentLoginForm [id=" + id + ",password=" + password + ", ]";
	}
}