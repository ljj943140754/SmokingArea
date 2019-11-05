package com.smokeroom.entity;

import java.util.Date;

public class Feedback {
    private Integer fb_id;

    private Integer fb_uid;

    private String fb_res;

    private String fb_content;

    private Integer fb_status;

    private String fb_rly_content;

    private Date fb_creation;

    public Integer getFb_id() {
        return fb_id;
    }

    public void setFb_id(Integer fb_id) {
        this.fb_id = fb_id;
    }

    public Integer getFb_uid() {
        return fb_uid;
    }

    public void setFb_uid(Integer fb_uid) {
        this.fb_uid = fb_uid;
    }

    public String getFb_res() {
        return fb_res;
    }

    public void setFb_res(String fb_res) {
        this.fb_res = fb_res;
    }

    public String getFb_content() {
        return fb_content;
    }

    public void setFb_content(String fb_content) {
        this.fb_content = fb_content;
    }

    public Integer getFb_status() {
        return fb_status;
    }

    public void setFb_status(Integer fb_status) {
        this.fb_status = fb_status;
    }

    public String getFb_rly_content() {
        return fb_rly_content;
    }

    public void setFb_rly_content(String fb_rly_content) {
        this.fb_rly_content = fb_rly_content;
    }

    public Date getFb_creation() {
        return fb_creation;
    }

    public void setFb_creation(Date fb_creation) {
        this.fb_creation = fb_creation;
    }
}