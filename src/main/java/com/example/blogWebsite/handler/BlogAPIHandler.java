package com.example.blogWebsite.handler;

import com.example.blogWebsite.model.BlogEntity;
import com.example.blogWebsite.model.BlogModel;
import com.example.blogWebsite.repository.BlogRepository;
import com.example.blogWebsite.utils.TransformUtils;
import org.opensearch.action.index.IndexResponse;
import org.opensearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class BlogAPIHandler {

    @Autowired
    BlogRepository blogRepository;

    public BlogModel createBlog(BlogModel blogModel) throws IOException {

        BlogEntity blogEntity = TransformUtils.getBlogEntity(blogModel);

        Date currentDate = TransformUtils.getCurrentDate();
        blogEntity.setCreatedDate(currentDate.toInstant());
        blogEntity.setUpdatedDate(currentDate.toInstant());

        String indexName = TransformUtils.getBlogIndexPattern(currentDate);

        IndexResponse indexResponse = blogRepository.insertBlog(indexName,blogEntity);
        blogModel = TransformUtils.entitytoDto(blogEntity);
        return blogModel;
    }

    public BlogModel getBlog(String blogId) throws IOException {
        SearchResponse searchResponse = blogRepository.getBlog(blogId);

        BlogEntity blogEntity = null;
        if (searchResponse != null && searchResponse.getHits().getHits().length > 0) {
            blogEntity = (BlogEntity) TransformUtils.readObjectFromJson(searchResponse.getHits().getHits()[0].getSourceAsString(),
                    BlogEntity.class);
        }
        BlogModel blogModel = TransformUtils.entitytoDto(blogEntity);
        return blogModel;
    }

    public List<BlogModel> getAllBlog() throws IOException {
        SearchResponse searchResponse = blogRepository.getAllBlog();
        List<BlogModel> blogModelList = new ArrayList<>();

        if (searchResponse != null && searchResponse.getHits().getHits().length > 0) {
            Arrays.stream(searchResponse.getHits().getHits()).forEach(searchHit -> {
                String sourceAsString = searchHit.getSourceAsString();
                BlogEntity blogEntity = (BlogEntity) TransformUtils.readObjectFromJson(sourceAsString,
                        BlogEntity.class);
                blogModelList.add(TransformUtils.entitytoDto(blogEntity));
            });
        }
        return blogModelList;
    }
}
