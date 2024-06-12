package com.example.blogWebsite.repository;

import com.example.blogWebsite.utils.TransformUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opensearch.action.index.IndexResponse;
import com.example.blogWebsite.model.BlogEntity;
import com.example.blogWebsite.model.BlogModel;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.client.OpenSearchClient;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.common.xcontent.XContentType;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;

@Repository
public class BlogRepository {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public IndexResponse insertBlog(String indexName, BlogEntity blogEntity) throws IOException {
        IndexRequest createIndexRequest = new IndexRequest(indexName);
        createIndexRequest.source(TransformUtils.convertToJson(blogEntity), XContentType.JSON);
        createIndexRequest.id(blogEntity.getBlogId());

        IndexResponse indexResponse = restHighLevelClient.index(createIndexRequest, RequestOptions.DEFAULT);
        return indexResponse;
    }

    public SearchResponse getBlog(String blogId) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("blog_*");
        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.boolQuery().filter(QueryBuilders.termQuery("blogId.keyword", blogId))));

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        return searchResponse;
    }

    public SearchResponse getAllBlog() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("blog_*");

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        return searchResponse;
    }
}
