package com.dreamdaisy.cart.mapper;

import com.dreamdaisy.cart.domain.Cart;
import com.dreamdaisy.cart.domain.CartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {
    
    Cart findByUserId(@Param("userId") Long userId);
    
    void insertCart(Cart cart);

    List<CartItem> findItemsByCartId(@Param("cartId") Long cartId);

    void insertCartItem(CartItem cartItem);
    
    void updateCartItem(CartItem cartItem);
}
