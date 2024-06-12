package com.example.blogWebsite.utils;

import com.example.blogWebsite.model.BlogEntity;
import com.example.blogWebsite.model.BlogModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import static com.example.blogWebsite.constant.Constant.BLOG_INDEX_FORMAT;
import static com.example.blogWebsite.constant.Constant.ELASTIC_INDEX_PREFIX_DATE_FORMAT;

@Slf4j
public final class TransformUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
    }

    public static BlogEntity getBlogEntity(BlogModel blogModel) {
        return BlogEntity.builder()
                .withTitle(blogModel.getTitle())
                .withContent(blogModel.getContent())
                .withAuthor(blogModel.getAuthor())
                .withTags(blogModel.getTags())
                .withBlogId(UUID.randomUUID().toString())
                .withBlogUrl(blogModel.getBlogUrl())
                .withBlogStatus(blogModel.getBlogStatus())
                .withBlogType(blogModel.getBlogType())
                .withBlogCategory(blogModel.getBlogCategory())
                .withBlogSubCategory(blogModel.getBlogSubCategory())
                .withBlogBody(blogModel.getBlogBody())
                .withImageUrl(blogModel.getImageUrl())
                .build();
    }

    public static Date getCurrentDate() {
        return Date.from(OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toInstant());
    }

    public static String getBlogIndexPattern(Date date) {
        return String.format(BLOG_INDEX_FORMAT, (date == null ? "*" : getElasticIndexPrefixDate(date)));
    }

    public static String getElasticIndexPrefixDate(Date date) {
        return new SimpleDateFormat(ELASTIC_INDEX_PREFIX_DATE_FORMAT).format(date);
    }

    public static String convertToJson(Object object) {
        try {

            return mapper.writeValueAsString(object);

        } catch (JsonProcessingException e) {
            System.out.println("Error while converting object to json "+e);
            return "{}";
        }
    }

    public static <T> Object readObjectFromJson(String json, Class<T> object) {
        try {
            return mapper.readValue(json, object);
        } catch (JsonProcessingException e) {
            log.error("error while converting json to Object: {}", e.getMessage(),e);
        }
        return null;
    }

    public static BlogModel entitytoDto(BlogEntity blogEntity) {
        BlogModel blogModel = new BlogModel();
        blogModel.setTitle(blogEntity.getTitle());
        blogModel.setContent(blogEntity.getContent());
        blogModel.setAuthor(blogEntity.getAuthor());
        blogModel.setTags(blogEntity.getTags());
        blogModel.setCreatedDate(blogEntity.getCreatedDate());
        blogModel.setUpdatedDate(blogEntity.getUpdatedDate());
        blogModel.setBlogId(blogEntity.getBlogId());
        blogModel.setBlogUrl(blogEntity.getBlogUrl());
        blogModel.setBlogStatus(blogEntity.getBlogStatus());
        blogModel.setBlogType(blogEntity.getBlogType());
        blogModel.setBlogCategory(blogEntity.getBlogCategory());
        blogModel.setBlogSubCategory(blogEntity.getBlogSubCategory());
        blogModel.setBlogBody(blogEntity.getBlogBody());
        blogModel.setImageUrl(blogEntity.getImageUrl());
        return blogModel;
    }
}
