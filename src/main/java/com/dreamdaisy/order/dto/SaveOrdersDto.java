package com.dreamdaisy.order.dto;

import lombok.Data;

import java.security.Timestamp;

@Data
public class SaveOrdersDto {
    private Long id;
    private Long memberId;
    private int totalAmount;
    private Timestamp orderDate;
}
