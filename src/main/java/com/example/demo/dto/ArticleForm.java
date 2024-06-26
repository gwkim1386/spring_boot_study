package com.example.demo.dto;

import com.example.demo.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title;
    private String content;



    public Article toEntity() {
        return new Article(null, title, content);
    }
    // 자 이 객체는 게시글을 받아주는 객체이다.
}
