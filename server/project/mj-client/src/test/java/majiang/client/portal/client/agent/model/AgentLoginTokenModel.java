package majiang.client.portal.client.agent.model;

import org.forkjoin.apikit.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

/**
 * @author  zuoge85 on 15/6/17.
 */
public class AgentLoginTokenModel implements ApiMessage {

	private String token;

	private String name;

	public AgentLoginTokenModel() {
	}

	public AgentLoginTokenModel(String token, String name) {
		this.token = token;
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<Entry<String, Object>> encode(String parent, List<Entry<String, Object>> $list) {

		if (token != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "token", token));
		}

		if (name != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "name", name));
		}

		return $list;
	}

	@Override
	public String toString() {
		return "AgentLoginTokenModel [token=" + token + ",name=" + name + ", ]";
	}
}