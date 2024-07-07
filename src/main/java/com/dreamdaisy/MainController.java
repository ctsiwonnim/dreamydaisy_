package com.dreamdaisy;

import com.dreamdaisy.item.domain.Item;
import com.dreamdaisy.member.domain.Member;
import com.dreamdaisy.item.service.ItemService;
import com.dreamdaisy.member.dto.SaveMemberDto;
import com.dreamdaisy.member.mapper.MemberMapper;
import com.dreamdaisy.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            BindingResult bindingResult,
            Model model) {

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

    @GetMapping("/cart")
    public String cartForm() {
        return "/item/cart";
    }

    @GetMapping("/additem")
    public String additemForm() {
        return "/item/additem";
    }

    @PostMapping("/additem")
    public String addItem(@RequestParam String name, @RequestParam int price, @RequestParam String itemscript) {
        Item item = Item.builder()
                .name(name)
                .price(price)
                .itemscript(itemscript)
                .build();

        itemService.save(item);
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String mypageForm(Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("member");
        if (member != null) {
            member = memberService.findById(member.getId());
            model.addAttribute("member", member);
        }
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

    @GetMapping("/mypage/mypagemodify")
    public String mypageModifyForm(Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("member");
        if (member != null) {
            member = memberService.findById(member.getId());
            model.addAttribute("member", member);
        }
        return "/member/mypagemodify";
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

    @GetMapping("/mypage/mypagedel")
    public String mypageDeleteForm(Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("member");
        if (member != null) {
            model.addAttribute("member", member);
        }
        return "/member/mypagedel";
    }

    @PostMapping("/mypage/mypagedel")
    public String mypageDelete(@RequestParam Long id, HttpSession session) {
        memberService.delete(id);
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutForm(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}