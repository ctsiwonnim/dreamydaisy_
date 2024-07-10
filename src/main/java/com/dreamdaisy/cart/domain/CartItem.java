package com.dreamdaisy.cart.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CartItem {
    private Long id;
    private Long cartId;
    private Long itemId;
    private int quantity;
}
