package com.blogpessoal.blog_pessoal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.blogpessoal.blog_pessoal.model")  // Especifica onde as entidades estão localizadas
public class BlogPessoalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogPessoalApplication.class, args);
    }
}
