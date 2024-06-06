package com.shop.demo.community.controller;


import com.shop.demo.community.domain.Article;
import com.shop.demo.community.dto.AddArticleRequest;
import com.shop.demo.community.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/api/article")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article article = articleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }
}
