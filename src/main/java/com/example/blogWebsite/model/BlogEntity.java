package com.example.blogWebsite.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BlogEntity {

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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
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

        private Builder() {
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }
        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }
        public Builder withTags(String tags) {
            this.tags = tags;
            return this;
        }
        public Builder withCreatedDate(Instant createdDate) {
            this.createdDate = createdDate;
            return this;
        }
        public Builder withUpdatedDate(Instant updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }
        public Builder withBlogId(String blogId) {
            this.blogId = blogId;
            return this;
        }
        public Builder withBlogUrl(String blogUrl) {
            this.blogUrl = blogUrl;
            return this;
        }
        public Builder withBlogStatus(String blogStatus) {
            this.blogStatus = blogStatus;
            return this;
        }
        public Builder withBlogType(String blogType) {
            this.blogType = blogType;
            return this;
        }
        public Builder withBlogCategory(String blogCategory) {
            this.blogCategory = blogCategory;
            return this;
        }
        public Builder withBlogSubCategory(String blogSubCategory) {
            this.blogSubCategory = blogSubCategory;
            return this;
        }
        public Builder withBlogBody(String blogBody) {
            this.blogBody = blogBody;
            return this;
        }

        public Builder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public BlogEntity build() {
            BlogEntity blogEntity = new BlogEntity();
            blogEntity.title = this.title;
            blogEntity.content = this.content;
            blogEntity.author = this.author;
            blogEntity.tags = this.tags;
            blogEntity.createdDate = this.createdDate;
            blogEntity.updatedDate = this.updatedDate;
            blogEntity.blogId = this.blogId;
            blogEntity.blogUrl = this.blogUrl;
            blogEntity.blogStatus = this.blogStatus;
            blogEntity.blogType = this.blogType;
            blogEntity.blogCategory = this.blogCategory;
            blogEntity.blogSubCategory = this.blogSubCategory;
            blogEntity.blogBody = this.blogBody;
            blogEntity.imageUrl = this.imageUrl;
            return blogEntity;
        }
    }
}
