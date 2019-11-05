package com.smokeroom.entity;

import java.util.Date;

public class Report {
    private Integer rp_id;

    private String rp_vedio;

    private String rp_audio;

    private Integer rp_worker_id;

    private Date rp_creation;

    private String rp_content;

    public Integer getRp_id() {
        return rp_id;
    }

    public void setRp_id(Integer rp_id) {
        this.rp_id = rp_id;
    }

    public String getRp_vedio() {
        return rp_vedio;
    }

    public void setRp_vedio(String rp_vedio) {
        this.rp_vedio = rp_vedio;
    }

    public String getRp_audio() {
        return rp_audio;
    }

    public void setRp_audio(String rp_audio) {
        this.rp_audio = rp_audio;
    }

    public Integer getRp_worker_id() {
        return rp_worker_id;
    }

    public void setRp_worker_id(Integer rp_worker_id) {
        this.rp_worker_id = rp_worker_id;
    }

    public Date getRp_creation() {
        return rp_creation;
    }

    public void setRp_creation(Date rp_creation) {
        this.rp_creation = rp_creation;
    }

    public String getRp_content() {
        return rp_content;
    }

    public void setRp_content(String rp_content) {
        this.rp_content = rp_content;
    }
}