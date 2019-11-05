package com.smokeroom.entity;

import java.util.Date;

public class Message {
    private Integer msg_id;

    private String msg_content;

    private Date msg_creation;

    private Integer msg_status;

    private Long msg_expire;

    public Integer getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Integer msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public Date getMsg_creation() {
        return msg_creation;
    }

    public void setMsg_creation(Date msg_creation) {
        this.msg_creation = msg_creation;
    }

    public Integer getMsg_status() {
        return msg_status;
    }

    public void setMsg_status(Integer msg_status) {
        this.msg_status = msg_status;
    }

    public Long getMsg_expire() {
        return msg_expire;
    }

    public void setMsg_expire(Long msg_expire) {
        this.msg_expire = msg_expire;
    }
}