package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// 엔티티임을 선언하고 id 정하고, db에 칼럼으로 들어갈 친구들 2개 정함.
@Entity
public class Article {
    @Id
    @GeneratedValue // 자동 숫자 생성
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}