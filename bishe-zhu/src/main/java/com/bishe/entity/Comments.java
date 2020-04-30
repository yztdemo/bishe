package com.bishe.entity;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "comments")
public class Comments {
    private Integer id;
    private String comment;
    private Integer user_id;
    private Integer follow_id;
    private String follow_name;
    private Date create_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFollow_id() {
        return follow_id;
    }

    public void setFollow_id(Integer follow_id) {
        this.follow_id = follow_id;
    }

    public String getFollow_name() {
        return follow_name;
    }

    public void setFollow_name(String follow_name) {
        this.follow_name = follow_name;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", user_id=" + user_id +
                ", follow_id=" + follow_id +
                ", follow_name='" + follow_name + '\'' +
                ", create_at=" + create_at +
                '}';
    }
}
