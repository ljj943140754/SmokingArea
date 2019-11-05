package com.smokeroom.entity;

import java.util.Date;

public class Facility {
    private Integer fy_id;

    private String fy_lat;

    private String fy_lng;

    private Integer fy_type;

    private String fy_name;

    private String fy_detail;

    private String fy_res_link;

    private Date fy_creation;

    private Integer fy_createdby;

    private Date fy_lastupdated;

    private Integer fy_lastupdatedby;

    private Integer fy_isdel;

    public Integer getFy_id() {
        return fy_id;
    }

    public void setFy_id(Integer fy_id) {
        this.fy_id = fy_id;
    }

    public String getFy_lat() {
        return fy_lat;
    }

    public void setFy_lat(String fy_lat) {
        this.fy_lat = fy_lat;
    }

    public String getFy_lng() {
        return fy_lng;
    }

    public void setFy_lng(String fy_lng) {
        this.fy_lng = fy_lng;
    }

    public Integer getFy_type() {
        return fy_type;
    }

    public void setFy_type(Integer fy_type) {
        this.fy_type = fy_type;
    }

    public String getFy_name() {
        return fy_name;
    }

    public void setFy_name(String fy_name) {
        this.fy_name = fy_name;
    }

    public String getFy_detail() {
        return fy_detail;
    }

    public void setFy_detail(String fy_detail) {
        this.fy_detail = fy_detail;
    }

    public String getFy_res_link() {
        return fy_res_link;
    }

    public void setFy_res_link(String fy_res_link) {
        this.fy_res_link = fy_res_link;
    }

    public Date getFy_creation() {
        return fy_creation;
    }

    public void setFy_creation(Date fy_creation) {
        this.fy_creation = fy_creation;
    }

    public Integer getFy_createdby() {
        return fy_createdby;
    }

    public void setFy_createdby(Integer fy_createdby) {
        this.fy_createdby = fy_createdby;
    }

    public Date getFy_lastupdated() {
        return fy_lastupdated;
    }

    public void setFy_lastupdated(Date fy_lastupdated) {
        this.fy_lastupdated = fy_lastupdated;
    }

    public Integer getFy_lastupdatedby() {
        return fy_lastupdatedby;
    }

    public void setFy_lastupdatedby(Integer fy_lastupdatedby) {
        this.fy_lastupdatedby = fy_lastupdatedby;
    }

    public Integer getFy_isdel() {
        return fy_isdel;
    }

    public void setFy_isdel(Integer fy_isdel) {
        this.fy_isdel = fy_isdel;
    }
}