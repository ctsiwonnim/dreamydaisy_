package com.dreamdaisy.cart.dto;

import lombok.Data;

@Data
public class SaveCartItemDto {
    private Long id;
    private Long cartId;
    private Long itemId;
    private int quantity;
}
