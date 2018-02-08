package majiang.client.portal.admin.model;

import com.isnowfox.core.PageResult;
import org.forkjoin.apikit.core.Message;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 */
@Message
public class PageModel<T> {
    private T[] list;
    private int count;
    private int page;
    private int pageSize;

    public PageModel() {
    }

    @SuppressWarnings("unchecked")
    public static  <T,D> PageModel<T> create(PageResult<D> pageResult, Class<T> itemCls) {
        PageModel<T> pageModel = new PageModel<T>();
        pageModel.count = pageResult.getCount();
        pageModel.page = pageResult.getPage();
        pageModel.pageSize = pageResult.getPageSize();

        pageModel.list = pageResult.getValue().stream().map(r -> {
            try {
                T t = itemCls.newInstance();
                BeanUtils.copyProperties(r, t);
                return t;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toArray(
                count -> (T[]) Array.newInstance(itemCls, pageResult.getValue().size())
        );

        return pageModel;
    }

    public T[] getList() {
        return list;
    }

    public void setList(T[] list) {
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

    @Override
    public String toString() {
        return "PageModel{" +
                "list=" + Arrays.toString(list) +
                ", count=" + count +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
