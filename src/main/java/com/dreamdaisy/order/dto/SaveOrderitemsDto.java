package com.dreamdaisy.order.dto;

import lombok.Data;

@Data
public class SaveOrderitemsDto {
    private Long id;
    private Long orderId;
    private Long itemId;
    private int quantity;
    private int price;
}
