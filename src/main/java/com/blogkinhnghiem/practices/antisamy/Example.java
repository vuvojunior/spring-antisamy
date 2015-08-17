package com.blogkinhnghiem.practices.antisamy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vvo on 8/17/15.
 */
@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class Example {

    @Autowired
    private ContentCleaner contentCleaner;

    @RequestMapping ("/clean")
    String cleanHtml(@RequestParam("html") String html) {

        String cleanedHtml = contentCleaner.clean(html);
        return cleanedHtml;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }

}
