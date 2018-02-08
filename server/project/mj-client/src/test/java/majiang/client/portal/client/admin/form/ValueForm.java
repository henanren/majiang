package majiang.client.portal.client.admin.form;

import org.forkjoin.apikit.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

/**

 */
public class ValueForm implements ApiMessage {

	private String value;

	public ValueForm() {
	}

	public ValueForm(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public List<Entry<String, Object>> encode(String parent, List<Entry<String, Object>> $list) {

		if (value != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "value", value));
		}

		return $list;
	}

	@Override
	public String toString() {
		return "ValueForm [value=" + value + ", ]";
	}
}