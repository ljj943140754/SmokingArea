package com.smokeroom.entity;

import java.util.Date;

public class Task {
    private Integer tsk_id;

    private String tsk_name;

    private Integer tsk_leader_id;

    private Integer tsk_type;

    private String tsk_time;

    private Date tsk_creation;

    private Integer tsk_createdby;

    private Date tsk_lastupdated;

    private Integer tsk_lastupdatedby;

    private Integer tsk_isdel;

    public Integer getTsk_id() {
        return tsk_id;
    }

    public void setTsk_id(Integer tsk_id) {
        this.tsk_id = tsk_id;
    }

    public String getTsk_name() {
        return tsk_name;
    }

    public void setTsk_name(String tsk_name) {
        this.tsk_name = tsk_name;
    }

    public Integer getTsk_leader_id() {
        return tsk_leader_id;
    }

    public void setTsk_leader_id(Integer tsk_leader_id) {
        this.tsk_leader_id = tsk_leader_id;
    }

    public Integer getTsk_type() {
        return tsk_type;
    }

    public void setTsk_type(Integer tsk_type) {
        this.tsk_type = tsk_type;
    }

    public String getTsk_time() {
        return tsk_time;
    }

    public void setTsk_time(String tsk_time) {
        this.tsk_time = tsk_time;
    }

    public Date getTsk_creation() {
        return tsk_creation;
    }

    public void setTsk_creation(Date tsk_creation) {
        this.tsk_creation = tsk_creation;
    }

    public Integer getTsk_createdby() {
        return tsk_createdby;
    }

    public void setTsk_createdby(Integer tsk_createdby) {
        this.tsk_createdby = tsk_createdby;
    }

    public Date getTsk_lastupdated() {
        return tsk_lastupdated;
    }

    public void setTsk_lastupdated(Date tsk_lastupdated) {
        this.tsk_lastupdated = tsk_lastupdated;
    }

    public Integer getTsk_lastupdatedby() {
        return tsk_lastupdatedby;
    }

    public void setTsk_lastupdatedby(Integer tsk_lastupdatedby) {
        this.tsk_lastupdatedby = tsk_lastupdatedby;
    }

    public Integer getTsk_isdel() {
        return tsk_isdel;
    }

    public void setTsk_isdel(Integer tsk_isdel) {
        this.tsk_isdel = tsk_isdel;
    }
}