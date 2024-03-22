package com.example.demo.controller;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }
    @PostMapping("/articles/create") // post요청 받기 위해서 이렇게
    public String createArticle(ArticleForm form){ // 이렇게 객체를 하나 넣어줌으로서 데이터를 여기로 받아오기 가능함.
        // 자 그럼 데이터를 어떻게 매핑해주지? => 인풋 받는 곳에서 연결해줌.
        // <textarea class="form-control" rows="3" name="content"></textarea> 이렇게 써줘서 content에 mapping
        System.out.println(form.toString());
        // Dto를 엔티티로
        Article article = form.toEntity();
        System.out.println(article.toString());
        // 리파지터리로 엔티티를 DB에 저장.
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }
    // 이정도까지만 하면 받아주기만 하고 그 이후 일은 안 함.
    // 하도록 하기 위해 정보를 DTO에 받아줘야 함.
}

// 자자 여기서 하는 행동은 무엇이냐.
// 폼 데이터를 통해서 데이터를 웹에서 서버로 전송할 것이다.
// 그럼 폼 데이터는 서버의 컨트롤러가 받는다. 받아서 객체에 저장한다. 이 때 저장되는 객체는 DTO라고 한다.
// 그럼 DTO는 최종적으로 DB에 저장된다.

// form 테그에 아무것도 안 쓰고 그냥 버튼을 누르면 아무일도 생겨나지 않는다. 그럼 어떻게 하냐면
// action 속성으로 어디에, method 속성으로 어떻게를 정해줘야 한다.
// <form class="container" action="/articles/create" method="post"> 이렇게 수정함으로서 create에 post로 보낸다라고 정해짐.
// 그럼 create에서 또 받아줘야겠지

// 이제 데베에 저장하는 방식에 대해 알아봅시다. dto에서 엔티티로 변환하고 엔티티를 리파지터리 통해 저장함.