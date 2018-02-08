package majiang.client.portal.client.admin.model;

import com.isnowfox.core.PageResult;
import org.springframework.beans.BeanUtils;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.forkjoin.apikit.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

/**

 */
public class PageModel<T extends ApiMessage> implements ApiMessage {

	private java.util.ArrayList<T> list;

	private int count;

	private int page;

	private int pageSize;

	public PageModel() {
	}

	public PageModel(java.util.ArrayList<T> list, int count, int page, int pageSize) {
		this.list = list;
		this.count = count;
		this.page = page;
		this.pageSize = pageSize;
	}

	public java.util.ArrayList<T> getList() {
		return list;
	}

	public void setList(java.util.ArrayList<T> list) {
		this.list = list;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void addList(T list) {
		if (this.list == null) {
			this.list = new java.util.ArrayList<T>();
		}
		this.list.add(list);
	}

	@Override
	public List<Entry<String, Object>> encode(String parent, List<Entry<String, Object>> $list) {

		if (list != null && (!list.isEmpty())) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).encode(parent + "list" + "[" + i + "].", $list);
			}
		}

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "count", count));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "page", page));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "pageSize", pageSize));

		return $list;
	}

	@Override
	public String toString() {
		return "PageModel [list=size:" + (list == null ? -1 : list.size()) + ",count=" + count + ",page=" + page
				+ ",pageSize=" + pageSize + ", ]";
	}
}