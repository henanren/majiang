package majiang.client.portal.client.admin.form;

import org.forkjoin.apikit.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

/**

 */
public class PayForm implements ApiMessage {

	private int id;

	private int gold;

	public PayForm() {
	}

	public PayForm(int id, int gold) {
		this.id = id;
		this.gold = gold;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	@Override
	public List<Entry<String, Object>> encode(String parent, List<Entry<String, Object>> $list) {

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "id", id));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "gold", gold));

		return $list;
	}

	@Override
	public String toString() {
		return "PayForm [id=" + id + ",gold=" + gold + ", ]";
	}
}