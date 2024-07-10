package com.dreamdaisy.member.controller;

import com.dreamdaisy.common.security.CustomUserDetails;
import com.dreamdaisy.member.domain.Member;
import com.dreamdaisy.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public String profile(
            @AuthenticationPrincipal CustomUserDetails member, Model model
    ) {
        Member findMember = memberService.findById(member.getMember().getId());
        model.addAttribute("member", findMember);

        return "/member/mypage";
    }

    @PostMapping("/mypage")
    public String mypageForm(@RequestParam("email") String email,
                             @RequestParam("name") String name,
                             @RequestParam("password") String password,
                             @RequestParam("phone") String phone,
                             HttpSession session,
                             Model model) {
        Member sessionMember = (Member) session.getAttribute("member");
        if (sessionMember != null) {
            Member member = memberService.findById(sessionMember.getId());
            model.addAttribute("member", member);
        }
        return "/member/mypage";
    }

    @GetMapping("/mypage/update")
    public String mypageModifyForm(Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("member");
        if (member != null) {
            member = memberService.findById(member.getId());
            model.addAttribute("member", member);
        }
        return "/member/mypage-update";
    }

    @PostMapping("/mypage/mypagemodify")
    public String mypageModify(
            @RequestParam Long id,
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String password,
            @RequestParam String phone,
            HttpSession session) {

        // 비밀번호 인코딩
        String encodedPassword = passwordEncoder.encode(password);
        Member member = Member.builder()
                .id(id)
                .email(email)
                .name(name)
                .password(encodedPassword)
                .phone(phone)
                .build();

        memberService.update(member);
        session.setAttribute("member", member);
        return "redirect:/mypage";
    }

    @GetMapping("/mypage/delete")
    public String mypageDeleteForm(Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("member");
        if (member != null) {
            model.addAttribute("member", member);
        }
        return "/member/mypage-delete";
    }


    @PostMapping("/mypage/delete")
    public String manpageDelete(@RequestParam Long id, HttpSession session) {
        memberService.delete(id);
        session.invalidate();
        return "redirect:/";
    }
}
