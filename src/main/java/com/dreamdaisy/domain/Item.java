package com.dreamdaisy.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long id;
    private String name;
    private int price;
    private String itemscript;

}