package com.dreamdaisy.cart.service;

import com.dreamdaisy.cart.domain.Cart;
import com.dreamdaisy.cart.domain.CartItem;
import com.dreamdaisy.cart.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartMapper cartMapper;

    @Transactional(readOnly = true)
    public Cart getCartByMemberId(Long memberId) {
        Cart cart = cartMapper.findByUserId(memberId);

        if (cart != null) {
            List<CartItem> items = cartMapper.findItemsByCartId(cart.getId());
            cart.setItems(items);
        }

        return cart;
    }
}
