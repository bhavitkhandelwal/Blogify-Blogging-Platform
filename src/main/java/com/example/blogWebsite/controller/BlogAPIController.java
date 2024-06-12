package com.example.blogWebsite.controller;

import com.example.blogWebsite.handler.BlogAPIHandler;
import com.example.blogWebsite.model.BlogEntity;
import com.example.blogWebsite.model.BlogModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://127.0.0.1:5500")
@Slf4j
public class BlogAPIController {

    @Autowired
    BlogAPIHandler blogAPIHandler;

    @PostMapping("/createBlog")
    public ResponseEntity createBlog(@RequestBody BlogModel blogModel) throws IOException {
        log.info("Create Blog Entered");
        blogModel = blogAPIHandler.createBlog(blogModel);
        return new ResponseEntity<>(blogModel, HttpStatus.CREATED);
    }

    @GetMapping("/getBlog/{blogId}")
    public ResponseEntity getBlog(@PathVariable("blogId") String blogId) throws IOException {
        log.info("Get Blog Entered");
        BlogModel blogModel = blogAPIHandler.getBlog(blogId);
        return new ResponseEntity<>(blogModel, HttpStatus.OK);
    }

    @GetMapping("/getAllBlog")
    public ResponseEntity getAllBlog() throws IOException {
        log.info("Get All Blog Entered");
        List<BlogModel> blogModel = blogAPIHandler.getAllBlog();
        return new ResponseEntity<>(blogModel, HttpStatus.OK);
    }
}
