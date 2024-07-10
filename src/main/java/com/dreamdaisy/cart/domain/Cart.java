package com.dreamdaisy.cart.domain;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Cart {
    private Long id;
    private Long memberId;
    private Long itemId;
    private int quantity;
    private List<CartItem> items;
}
