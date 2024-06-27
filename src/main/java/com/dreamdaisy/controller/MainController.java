package com.dreamdaisy.controller;

import com.dreamdaisy.domain.Member;
import com.dreamdaisy.service.ItemService;
import com.dreamdaisy.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping(value = "/")
    public String getMainPage(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "main";
    }

    @GetMapping(value = "/member/join")
    public String join() {
        return "join";
    }

    @PostMapping(value = "/member/join")
    public String getMain(@RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam String phone) {
        Member member = Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .phone(phone)
                .build();

        memberService.save(member);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginFrom() {
        return "login";
    }
}
