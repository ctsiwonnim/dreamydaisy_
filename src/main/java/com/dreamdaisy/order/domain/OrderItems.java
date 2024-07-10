package com.dreamdaisy.order.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItems {
    private Long id;
    private Long orderId;
    private Long itemId;
    private int quantity;
    private int price;
}
