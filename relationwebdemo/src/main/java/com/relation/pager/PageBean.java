package com.relation.pager;

import java.util.List;

/**
 * Created by T.Cage on 2017/1/29.
 */
public class PageBean<T> {
    private int pageCount;//当前页码
    private int totalRecord;//总记录数
    private int pageSize;//每页记录数
    private String url;//请求路径
    private List<T> beanList;
    private int totalPage;

    public int getTotalPage(){
        int tp=totalRecord/pageSize;
        return totalRecord%pageSize==0?tp:tp+1;
    }
    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<T> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }
}
