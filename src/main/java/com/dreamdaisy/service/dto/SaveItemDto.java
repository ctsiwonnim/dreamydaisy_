package com.dreamdaisy.service.dto;

import lombok.Data;

@Data
public class SaveItemDto {
    private Long id;
    private String name;
    private int price;
    private String itemscript;
}
