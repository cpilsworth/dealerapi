package uk.co.diffa.dealerapi.resource;

import com.google.common.collect.Lists;

import java.util.List;

public class Paged<T> {

    private long total;
    private int page;
    private int pageSize;
    private final List<T> items;

    public long getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public Paged(final long total, final int page, final int pageSize, Iterable<T> items) {
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
        this.items = Lists.newArrayList(items);
    }
}
