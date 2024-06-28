package com.dreamdaisy.controller;

import com.dreamdaisy.domain.Item;
import com.dreamdaisy.domain.Member;
import com.dreamdaisy.service.ItemService;
import com.dreamdaisy.service.MemberService;
import jakarta.servlet.http.HttpSession;
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

    //로그인 구현중
    @PostMapping("/member/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        Member member = memberService.findByEmailAndPassword(email, password);

        if (member != null) {
            session.setAttribute("member", member);//수정
            model.addAttribute("member", member);
            return "redirect:/";
        } else {
            model.addAttribute("errorMessage", "로그인 실패");
            return "login";
        }
    }

    @GetMapping("/member/cart")
    public String cartFrom() {
        return "cart";
    }

    @GetMapping("/member/additem")
    public String additemFrom() {
        return "additem";
    }

    @PostMapping("/member/additem")
    public String addItem(@RequestParam String name, @RequestParam int price, @RequestParam String itemscript) {
        Item item = Item.builder()
                .name(name)
                .price(price)
                .itemscript(itemscript)
                .build();

        itemService.save(item);
        return "redirect:/";
    }

    @GetMapping("/member/mypage")
    public String mypageFrom(Model model) {
        // Member 정보를 나중에 가져올 예정이므로 null로 설정
        model.addAttribute("member", null);
        return "mypage";
    }

    @GetMapping("/member/mypagemodify")
    public String mypageModifyForm() {
        return "mypagemodify";
    }

//    @GetMapping("/item/details")
//    public String getItemDetails(@RequestParam Long id, Model model) {
//        Item item = itemService.findById(id);
//        model.addAttribute("item", item);
//        return "item";
//    }

    @GetMapping("/member/logout")
    public String logoutForm(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
