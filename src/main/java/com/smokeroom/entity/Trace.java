package com.smokeroom.entity;

import java.util.Date;

public class Trace {
    private Integer te_id;

    private Integer te_fy_id;

    private Integer te_u_id;

    private Date te_fy_creationtime;

    private Long te_last_second;

    public Integer getTe_id() {
        return te_id;
    }

    public void setTe_id(Integer te_id) {
        this.te_id = te_id;
    }

    public Integer getTe_fy_id() {
        return te_fy_id;
    }

    public void setTe_fy_id(Integer te_fy_id) {
        this.te_fy_id = te_fy_id;
    }

    public Integer getTe_u_id() {
        return te_u_id;
    }

    public void setTe_u_id(Integer te_u_id) {
        this.te_u_id = te_u_id;
    }

    public Date getTe_fy_creationtime() {
        return te_fy_creationtime;
    }

    public void setTe_fy_creationtime(Date te_fy_creationtime) {
        this.te_fy_creationtime = te_fy_creationtime;
    }

    public Long getTe_last_second() {
        return te_last_second;
    }

    public void setTe_last_second(Long te_last_second) {
        this.te_last_second = te_last_second;
    }
}