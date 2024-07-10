package com.dreamdaisy.cart.dto;

import lombok.Data;

@Data
public class SaveCartDto {
    private Long id;
    private Long memberId;
    private Long itemId;
    private int quantity;
}
