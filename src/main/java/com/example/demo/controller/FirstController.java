package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        // 모델을 추가하면, 모델에 에트리뷰트를 추가해서 html상에 나타낼 수 있음.
        model.addAttribute("username", "kgw");
        return "greeting";
    }
    @GetMapping("/bye")
    public String seeYouNextTime(Model model){
        // 모델을 추가하면, 모델에 에트리뷰트를 추가해서 html상에 나타낼 수 있음.
        model.addAttribute("username", "kgw");
        // 이 위 코드를 보면 model 즉 데이터 관련된 모델 객체 통해서 username에 kgw갖다 넣을 수 있음.
        return "goodbye";
    }
}

// 질문점: mustache파일은 무엇인가?
// 정리: MVC 패턴은 모델 뷰 컨트롤러 패턴을 말한다. 뷰 템플릿으로 동일한 화면에, 모델을 통해 각 유저들의 데이터를 받아와서, 컨트롤러는 그걸 가져오라고 오더한다.