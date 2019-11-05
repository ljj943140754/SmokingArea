package com.smokeroom.entity;

import java.util.Date;

public class ActivityJoinIn {
    private Integer ji_at_id;

    private Integer ji_uid;

    private Date ji_creation;

    public Integer getJi_at_id() {
        return ji_at_id;
    }

    public void setJi_at_id(Integer ji_at_id) {
        this.ji_at_id = ji_at_id;
    }

    public Integer getJi_uid() {
        return ji_uid;
    }

    public void setJi_uid(Integer ji_uid) {
        this.ji_uid = ji_uid;
    }

    public Date getJi_creation() {
        return ji_creation;
    }

    public void setJi_creation(Date ji_creation) {
        this.ji_creation = ji_creation;
    }
}