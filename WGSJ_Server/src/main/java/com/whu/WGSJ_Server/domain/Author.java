package com.whu.WGSJ_Server.domain;

import com.baomidou.mybatisplus.annotations.TableId;

public class Author {
    @TableId
    private String authorId;

    private String name;

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
