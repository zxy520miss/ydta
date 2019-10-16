package com.nuo.ydta.utils;

public class NuoPage {
    private final int pageIndex;
    private final int pageSize;

    public NuoPage() {
        this.pageIndex = 0;
        this.pageSize = 10;
    }

    public NuoPage(Integer pageIndex) {
        if (pageIndex == null || pageIndex <= 0) {
            pageIndex = 1;
        }
        this.pageIndex = --pageIndex;
        pageSize = 10;
    }

    public int pageIndex() {
        return pageIndex;
    }

    public int pageSize() {
        return pageSize;
    }
}
