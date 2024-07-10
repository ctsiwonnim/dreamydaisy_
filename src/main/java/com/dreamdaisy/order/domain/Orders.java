package com.dreamdaisy.order.domain;

import lombok.*;
import java.security.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Orders {
    private Long id;
    private Long memberId;
    private int totalAmount;
    private Timestamp orderDate;
}
