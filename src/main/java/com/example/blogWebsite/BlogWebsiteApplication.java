package com.example.blogWebsite;

import com.example.blogWebsite.controller.BlogAPIController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.blogWebsite"})
public class BlogWebsiteApplication {

	@Autowired
	BlogAPIController blogAPIController;


	public static void main(String[] args) {


		SpringApplication.run(BlogWebsiteApplication.class, args);


	}
}
