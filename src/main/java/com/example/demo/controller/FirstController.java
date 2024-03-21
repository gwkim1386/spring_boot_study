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
        return "goodbye";
    }
}

// 질문점: mustache파일은 무엇인가?