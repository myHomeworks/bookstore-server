package com.whu.bookstore_server.utils;

import java.util.List;

public class ClassList {
    private String id;
    private String name;
    private List<String> pages;
    private List<String> pagesId;

    public List<String> getPagesId() {
        return pagesId;
    }

    public void setPagesId(List<String> pagesId) {
        this.pagesId = pagesId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPages() {
        return pages;
    }

    public void setPages(List<String> pages) {
        this.pages = pages;
    }
}
