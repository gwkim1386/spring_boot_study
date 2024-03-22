package com.example.demo.controller;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create") // post요청 받기 위해서 이렇게
    public String createArticle(ArticleForm form) { // 이렇게 객체를 하나 넣어줌으로서 데이터를 여기로 받아오기 가능함.
        // 자 그럼 데이터를 어떻게 매핑해주지? => 인풋 받는 곳에서 연결해줌.
        // <textarea class="form-control" rows="3" name="content"></textarea> 이렇게 써줘서 content에 mapping
        log.info(form.toString());

        // Dto를 엔티티로
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());
        // 리파지터리로 엔티티를 DB에 저장.
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/articles/"+saved.getId(); // 서브밋 누르면 이제 여기로 리다이렉트 됨.
    }
    // 이정도까지만 하면 받아주기만 하고 그 이후 일은 안 함.
    // 하도록 하기 위해 정보를 DTO에 받아줘야 함.

    // 이제 데이터베이에서 조회를 해보겠습니다.
    // 조회는 다음과 같은 매커니즘으로 작동합니다.
    // url을 통해서 요청을 합니다
    // 그럼 컨트롤러에서 요청 받아주고 리파지터리 통해서 DB 조회 요청합
    // 그럼 데이터를 엔티티로 반환하고, 그 데이터를 뷰 템플릿에 반환한다.

    // 이제 컨트롤러 만들기부터 시작
    @GetMapping("/articles/{id}") // 이걸 통해서 그럼 요청은 받아 줄 수가 있지.
    public String show(@PathVariable Long id, Model mOdel) { // 패스 베리어블은 url주소의 id를 받아서 오는 그런 역할
        // 모델을 통해 데이터를 등록하지요
        log.info("id:" + id);
        // 받은 요청, id를 통해서 이제 리파지터리에게 조회하게 하고 받아오기 하면 됨.

        Article articleEntity = articleRepository.findById(id).orElse(null); // 오류의 이유는 옵셔널 타입이기 때문이다. .orElse() 통해 지우기 가능
        mOdel.addAttribute("article", articleEntity); // 엔티티 객체를 아티클 이라는 이름으로 등록함.
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model) {
        // 여기서 할 일
        // 1 모든 데이터 가져오기 => 레파지터리에서 findALl하면 됨.
        List<Article> articleEntityList = articleRepository.findAll(); // 여기서 리파지터리 이용해서 모든 정보 가져옴, d이쪽 할 때 관계성 한번 다시 생각해보기.
        // 모델에 가져온 데이터들 등록하기
        model.addAttribute("articleList", articleEntityList);
        // 뷰 페이지 설정하기.
        return "articles/index";
    }
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