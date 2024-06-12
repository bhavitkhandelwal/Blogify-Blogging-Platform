package com.example.blogWebsite.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BlogModel {

    private String title;
    private String content;
    private String author;
    private String tags;
    private Instant createdDate;
    private Instant updatedDate;
    private String blogId;
    private String blogUrl;
    private String blogStatus;
    private String blogType;
    private String blogCategory;
    private String blogSubCategory;
    private String blogBody;
    private String imageUrl;

    public BlogModel() {
    }

    @Override
    public String toString() {
        return "BlogModel{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", tags='" + tags + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", blogId='" + blogId + '\'' +
                ", blogUrl='" + blogUrl + '\'' +
                ", blogStatus='" + blogStatus + '\'' +
                ", blogType='" + blogType + '\'' +
                ", blogCategory='" + blogCategory + '\'' +
                ", blogSubCategory='" + blogSubCategory + '\'' +
                ", blogBody='" + blogBody + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

}
