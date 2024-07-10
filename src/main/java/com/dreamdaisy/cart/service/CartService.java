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

    @Transactional
    public void updateCartItem(Long cartItemId, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemId);
        cartItem.setQuantity(quantity);
        cartMapper.updateCartItem(cartItem);
    }

    @Transactional
    public void addToCart(Long memberId, Long itemId) {
        Cart cart = cartMapper.findByUserId(memberId);
        if (cart == null) {
            cart = new Cart();
            cart.setMemberId(memberId);
            cartMapper.insertCart(cart);
        }

        CartItem cartItem = cartMapper.findCartItemByCartIdAndItemId(cart.getId(), itemId);
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setCartId(cart.getId());
            cartItem.setItemId(itemId);
            cartItem.setQuantity(1); // Default quantity
            cartMapper.insertCartItem(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartMapper.updateCartItem(cartItem);
        }
    }
}
