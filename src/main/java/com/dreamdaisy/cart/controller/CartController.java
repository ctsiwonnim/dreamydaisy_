package com.dreamdaisy.cart.controller;

import com.dreamdaisy.cart.domain.Cart;
import com.dreamdaisy.cart.service.CartService;
import com.dreamdaisy.common.security.CustomUserDetails;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart")
    public String getCart(Model model, HttpSession session) {
        Long memberId = (Long) session.getAttribute("userId");
        if (memberId == null) {
            return "redirect:/login";
        }

        Cart cart = cartService.getCartByMemberId(memberId);
        model.addAttribute("cart", cart);
        return "cart/cart";
    }

    @PostMapping("/cart/item/{cartItemId}/update")
    public String updateCartItem(@PathVariable Long cartItemId,
                                 @RequestParam int quantity,
                                 HttpSession session) {
        Long memberId = (Long) session.getAttribute("userId");
        if (memberId == null) {
            return "redirect:/login";
        }

        cartService.updateCartItem(cartItemId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long itemId, HttpSession session) {
        Long memberId = (Long) session.getAttribute("userId");
        if (memberId == null) {
            return "redirect:/login";
        }

        cartService.addToCart(memberId, itemId);
        return "redirect:/cart";
    }
}
