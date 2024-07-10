package com.dreamdaisy.cart.controller;

import com.dreamdaisy.cart.domain.Cart;
import com.dreamdaisy.cart.service.CartService;
import com.dreamdaisy.common.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class CartController {

    private final CartService cartService;

    @GetMapping("/my/cart")
    public String cartForm(
            @AuthenticationPrincipal CustomUserDetails member,
            Model model) {

        Cart cart = cartService.getCartByMemberId(member.getMember().getId());
        model.addAttribute("cart", cart);

        return "/item/cart";
    }


}
