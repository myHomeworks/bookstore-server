package com.whu.bookstore_server.domain;

import com.baomidou.mybatisplus.annotations.TableId;

public class Classification {
    @TableId
    private String classId;

    private String Name;

    private String fatherId;

    private String time;

    // 是否有子类
    private Integer ifFather;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getIfFather() {
        return ifFather;
    }

    public void setIfFather(Integer ifFather) {
        this.ifFather = ifFather;
    }
}
