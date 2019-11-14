package com.smokeroom.entity;

import java.util.Date;

//消息表
public class Message {
    private Integer msg_id;

    private String msg_content;

    private Date msg_creation;

    private Integer msg_status;

    private Long msg_expire;
    
    private String msg_title;

    
    public String getMsg_title() {
		return msg_title;
	}

	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}

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

	@Override
	public String toString() {
		return "Message [msg_id=" + msg_id + ", msg_content=" + msg_content + ", msg_creation=" + msg_creation
				+ ", msg_status=" + msg_status + ", msg_expire=" + msg_expire + ", msg_title=" + msg_title + "]";
	}
    
    
}