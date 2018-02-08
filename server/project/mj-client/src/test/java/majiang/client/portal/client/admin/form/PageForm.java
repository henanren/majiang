package majiang.client.portal.client.admin.form;

import org.forkjoin.apikit.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

/**

 */
public class PageForm implements ApiMessage {

	private int pageSize;

	private int page;

	public PageForm() {
	}

	public PageForm(int pageSize, int page) {
		this.pageSize = pageSize;
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public List<Entry<String, Object>> encode(String parent, List<Entry<String, Object>> $list) {

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "pageSize", pageSize));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "page", page));

		return $list;
	}

	@Override
	public String toString() {
		return "PageForm [pageSize=" + pageSize + ",page=" + page + ", ]";
	}
}