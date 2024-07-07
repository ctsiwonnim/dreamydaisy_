package com.dreamdaisy;

import com.dreamdaisy.item.domain.Item;
import com.dreamdaisy.member.domain.Member;
import com.dreamdaisy.item.service.ItemService;
import com.dreamdaisy.member.mapper.MemberMapper;
import com.dreamdaisy.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping(value = "/")
    public String getMainPage(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "main";
    }

    @GetMapping(value = "/join")
    public String join() {
        return "member/join";
    }

    @PostMapping(value = "/join")
    public String getMain(@RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam String phone) {
        Member member = Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .phone(phone)
                .build();

        memberService.save(member);
        return "/member/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/member/login";
    }

    @PostMapping("/login-proc")
    public String loginProcess(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               HttpSession session,
                               Model model) {
        Member member = memberService.findByEmailAndPassword(email, password);
        if (member != null) {
            session.setAttribute("member", member);
            return "redirect:/mypage";
        } else {
            model.addAttribute("loginError", true);
            return "/member/login";
        }
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

        Member member = Member.builder()
                .id(id)
                .email(email)
                .name(name)
                .password(password)
                .phone(phone)
                .build();

        memberService.update(member);
        session.setAttribute("member", member);
        return "redirect:/";
    }

    @GetMapping("/mypage/mypagedel")
    public String mypageDeleteForm(Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("member");
        if (member != null) {
            model.addAttribute("member", member);
        }
        return "/member/mypagedel";
    }

//    @PostMapping("/member/delete")
//    public String deleteMember(@RequestParam Long id, HttpSession session) {
//        memberService.delete(id);
//        session.invalidate();
//        return "redirect:/";
//    }

    @GetMapping("/logout")
    public String logoutForm(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

//    @PostMapping("/login-proc")
//    public String loginProc(@RequestParam("email") String email,
//                            @RequestParam("password") String password,
//                            Model model,
//                            RedirectAttributes redirectAttributes) {
//
//        // 사용자 인증 로직
//        boolean isAuthenticated = MemberMapperSS.authenticate(email, password);
//
//        if (isAuthenticated) {
//            // 인증 성공 시 세션에 사용자 정보 저장 등 필요한 추가 작업 수행
//            // 여기서는 세션에 사용자 정보를 저장하는 예시를 보여줍니다 (세션에 저장된 사용자 정보는 SecurityConfig에서 처리)
//            // SecurityContext에서 인증 객체를 가져와서 세션에 저장할 수도 있습니다.
//            // SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            // 로그인 성공 후 홈 페이지로 리다이렉트
//            return "redirect:/";
//        } else {
//            // 인증 실패 시 에러 메시지를 모델에 추가하여 로그인 페이지로 다시 이동
//            model.addAttribute("errorMessage", "Invalid email or password");
//            return "login"; // 로그인 페이지로 다시 이동
//        }
//    }
//로그인 구현중
//    @PostMapping("/login")
//    public String login(
//            @RequestParam String email,
//            @RequestParam String password,
//            HttpSession session,
//            Model model) {
//        Member member = memberService.findByEmailAndPassword(email, password);
//
//        if (member != null) {
//            session.setAttribute("member", member);//수정
//            model.addAttribute("member", member);
//            return "redirect:/";
//        } else {
//            model.addAttribute("errorMessage", "로그인 실패");
//            return "/member/login";
//        }
//    }
