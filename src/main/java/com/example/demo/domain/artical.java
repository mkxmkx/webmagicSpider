package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 实体类
 * 定义了要存到数据库中的数据类型
 */

@Entity
public class artical {

    //每个实体类都要有一个Id
    @Id
    @GeneratedValue
    private Long articalId;

    private String title;

    private String time;

    private String author;

    private String URL;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    private String content;

    public artical() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString()
    {
        return "artical{" +
                "title=" + title +
                "author=" + author +
                "time=" + time +
                "}";
    }
}

