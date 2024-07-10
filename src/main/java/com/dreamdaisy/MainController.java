package com.dreamdaisy;

import com.dreamdaisy.item.service.ItemService;
import com.dreamdaisy.member.domain.Member;
import com.dreamdaisy.member.dto.SaveMemberDto;
import com.dreamdaisy.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MemberService memberService;
    private final ItemService itemService;
    private final PasswordEncoder passwordEncoder; // 비밀번호 인코더 추가

    // 웰컴페이지 랜더링 API
    @GetMapping(value = "/")
    public String getMainPage(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "main";
    }

    // 회원가입 페이지 랜더링 API
    @GetMapping(value = "/join")
    public String joinPage(Model model) {
        model.addAttribute("saveMemberDto", new SaveMemberDto());
        return "member/join";
    }

    // 회원가입 API
    @PostMapping(value = "/join")
    public String register(
            @Valid SaveMemberDto saveMemberDto,
            BindingResult bindingResult) {

        // 값 대입에 오류가 존재한다면 회원가입 페이지 다시 리턴
        if (bindingResult.hasErrors()) {
            return "member/join";
        }

        // 비밀번호 인코딩
        String encodedPassword = passwordEncoder.encode(saveMemberDto.getPassword());

        Member member = Member.builder()
                .email(saveMemberDto.getEmail())
                .name(saveMemberDto.getName())
                .password(encodedPassword)
                .phone(saveMemberDto.getPhone())
                .build();

        memberService.save(member);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/member/login";
    }

    @GetMapping("/login/error")
    public String loginErrorForm(Model model) {
        model.addAttribute("loginError", "아이디 또는 비밀번호를 확인해 주세요.");
        return "/member/login";
    }
}