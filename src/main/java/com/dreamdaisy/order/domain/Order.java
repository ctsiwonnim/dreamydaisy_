package com.dreamdaisy.order.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private Integer quantity;
    private Integer totalprice;
    private String orderer;
    private String itemname;
}
