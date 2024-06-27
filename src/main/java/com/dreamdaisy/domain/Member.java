package com.dreamdaisy.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Member {
    private Long id;
    private String email;
    private String name;
    private String password;
    private String phone;
}
