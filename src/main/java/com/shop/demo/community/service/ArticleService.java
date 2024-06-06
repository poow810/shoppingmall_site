package com.shop.demo.community.service;

import com.shop.demo.community.domain.Article;
import com.shop.demo.community.dto.AddArticleRequest;
import com.shop.demo.community.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article save(AddArticleRequest request) {
        return articleRepository.save(request.toEntity());
    }
}
