package majiang.client.portal.admin.form;

import org.forkjoin.apikit.core.Message;
import org.hibernate.validator.constraints.Range;

/**
 *
 */
@Message
public class PageForm {
    @Range(min = 0, max = 100)
    private int pageSize;
    private int page;

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
    public String toString() {
        return "PageForm{" +
                "pageSize=" + pageSize +
                ", page=" + page +
                '}';
    }
}
