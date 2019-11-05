package com.nuo.ydta.utils;

public class NuoPage {
    private  int pageIndex;
    private  int pageSize;

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

    public NuoPage(Integer pageIndex ,Integer pageSize) {
        if (pageIndex == null || pageIndex <= 0) {
            pageIndex = 1;
        }

        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }else{
            this.pageSize = pageSize;
        }
        this.pageIndex = --pageIndex;
    }

    public int pageIndex() {
        return pageIndex;
    }

    public int pageSize() {
        return pageSize;
    }
}
